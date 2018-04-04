package com.landbay.peer2peer.services;

import com.landbay.peer2peer.entities.Lender;
import com.landbay.peer2peer.entities.Loan;
import com.landbay.peer2peer.entities.LoanInvestment;
import com.landbay.peer2peer.repositories.ILenderRepository;
import com.landbay.peer2peer.repositories.ILoanInvestmentRepository;
import com.landbay.peer2peer.repositories.ILoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<LoanInvestment> getLoan(long id)
    {
        List<LoanInvestment>  investments = loanInvestmentRepository.findAllByLoanId(id);
        return investments;
    }

    public void investIntoLoan(LoanInvestment loanInvestment)
    {
        //
        // First, check if
        //
        String name = loanInvestment.getLenderName();
        long id = lenderRepository.findByName(name);

        // To change
        if(id == -1)
        {
            Lender lender = new Lender(name);
            lenderRepository.save(lender);
        }

        loanInvestmentRepository.save(loanInvestment);
    }

  //  @Transactional
    public void deleteLoan(long id)
    {
        loanRepository.deleteById(id);

        loanInvestmentRepository.deleteByLoanId(id);
    }
}
