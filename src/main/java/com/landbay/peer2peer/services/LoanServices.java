package com.landbay.peer2peer.services;

import com.landbay.peer2peer.entities.Lender;
import com.landbay.peer2peer.entities.Loan;
import com.landbay.peer2peer.entities.LoanDetails;
import com.landbay.peer2peer.entities.LoanInvestment;
import com.landbay.peer2peer.repositories.ILenderRepository;
import com.landbay.peer2peer.repositories.ILoanInvestmentRepository;
import com.landbay.peer2peer.repositories.ILoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServices
{
    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private ILoanInvestmentRepository loanInvestmentRepository;

    @Autowired
    private ILenderRepository lenderRepository;

    public Loan createLoan(Loan loan)
    {
        loan.setAmountInvested(0.);
        loan.setTotalRepayment(0.);

        Loan savedLoan = loanRepository.save(loan);
        return savedLoan;
    }

    public LoanDetails getLoan(long id) throws Exception
    {
        LoanDetails loanDetails = null;
        Loan loan = getLoanIfExists(id);
        if(loan == null)
        {
            throw new Exception("Loan cannot be fetch because it does not exists");
        }
        List<LoanInvestment>  investments = loanInvestmentRepository.findAllByLoanId(id);
        loanDetails = new LoanDetails(loan, investments);

        return loanDetails;
    }

    public void investIntoLoan(LoanInvestment loanInvestment) throws Exception
    {
        //
        // First, check if the loan exist
        // Then, check that the amount invested is not higher than the amount left to invest
        // If the lender does not exist, the service will create one on the fly
        // And finally, save the investment
        //
        validateInvestment(loanInvestment);
        createLender(loanInvestment.getLenderName());
        updateLoan(loanInvestment);
    }

  //  @Transactional
    public void deleteLoan(long id)
    {
        loanRepository.deleteById(id);
        loanInvestmentRepository.deleteByLoanId(id);
    }


    private Loan validateInvestment(LoanInvestment investment) throws Exception
    {
        Loan loan = getLoanIfExists(investment.getLoanId());
        if (loan != null)
        {
            double amountInvested = loan.getAmountInvested();
            double amountRequired = loan.getAmountRequired();

            double remaining = amountRequired - amountInvested;
            if(investment.getAmount() > remaining)
            {
                throw new Exception("The investment cannot be accepted. " +
                        "The amount invested is higher than the investement remaining on this loan");
            }
        }
        else
        {
            throw new Exception("The given loan Id doesn't exist. We can't invest your money");
        }

        return loan;
    }

    private void createLender(String name)
    {
        // Needs to use object because it can be null
        Long lenderId = lenderRepository.findByName(name);

        if(lenderId == null)
        {
            Lender lender = new Lender(name);
            lenderRepository.save(lender);
        }
    }

    private void updateLoan(LoanInvestment loanInvestment) throws Exception
    {
        //
        // 1st save the investment
        // Then update the amountInvested of the loan
        //
        Loan loan = validateInvestment(loanInvestment);
        loanInvestmentRepository.save(loanInvestment);

        loan.setAmountInvested(loan.getAmountInvested() + loanInvestment.getAmount());
        calculateInvestmentRepayment(loanInvestment, loan);

        loanRepository.save(loan);
    }

    private Loan getLoanIfExists(long id)
    {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        Loan loan = null;
        if (loanOptional.isPresent())
        {
            loan = loanOptional.get();
        }

        return loan;
    }

    private void calculateInvestmentRepayment(LoanInvestment investment, Loan loan)
    {
        //
        // I assume that the the rate is per year, so dividing that by 365
        //
        System.out.println("Raw Period : "+ investment.getInvestmentPeriod()
        );
        double period = investment.getInvestmentPeriod() / 365.;
        System.out.println("Period : "+period);
        double amount = investment.getAmount();
        double decimalRate = LoanInvestment.ERateType.EFixedRate.getRate() / 100 ;
        System.out.println("Rate : "+decimalRate);

        //
        // The total amount is calculated following this formula A = P(1 + rt)
        // where P is the principal amount
        // r the annual rate in decimal
        // t the time period
        //

        double repayment = new BigDecimal( amount * (1 + (decimalRate * period))).setScale(2, RoundingMode.CEILING).doubleValue();
        double interests = new BigDecimal( repayment - amount ).setScale(2, RoundingMode.CEILING).doubleValue();

        investment.setInterests(interests);

        // Update total repayment
        loan.setTotalRepayment(loan.getTotalRepayment() + repayment);

    }
}
