package com.landbay.peer2peer.controllers;

import com.landbay.peer2peer.entities.LoanDetails;
import com.landbay.peer2peer.entities.LoanInvestment;
import com.landbay.peer2peer.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A Rest Controller responsible for investment operations
 */
@RequestMapping(value = "/investment")
@RestController
public class InvestmentController
{

    @Autowired
    private LoanServices loanServices;

    /**
     * A GET endpoint returning the loan details
     * containing investments
     * @param id the loan Id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLoanDetails(@PathVariable("id") long id)
    {
        LoanDetails loanDetails = null;
        try
        {
            loanDetails = loanServices.getLoan(id);
            return ResponseEntity.status(HttpStatus.OK).body(loanDetails);
        }
        catch (Exception e)
        {
            return ExceptionConverter.convertException(e);
        }

    }

    /**
     * A POST endpoint creating a new investment into a loan
     * @param loanInvestment
     */
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> investIntoLoan(@RequestBody LoanInvestment loanInvestment)
    {
        try
        {
            validateInvestment(loanInvestment);
            loanServices.investIntoLoan(loanInvestment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ExceptionConverter.convertException(e);
        }
    }

    /**
     * Validation of input parameters
     * @param investment the input parameter
     */
    private void validateInvestment(LoanInvestment investment)
    {
        String lenderName = investment.getLenderName();
        long loanId = investment.getLoanId();
        double amount = investment.getAmount();
        int period = investment.getInvestmentPeriod();
        String rateType = investment.getRateType();

        if(lenderName == null || lenderName.isEmpty())
        {
            throw new IllegalArgumentException("Lender name needs to be provided");
        }

        if(loanId < 0)
        {
            throw new IllegalArgumentException("Loan id needs to be a positive integer");
        }

        if(amount < 0.)
        {
            throw new IllegalArgumentException("The amount invested needs to be a positive float");
        }

        if(period <= 0)
        {
            throw new IllegalArgumentException("The time period in days must be a positive value");
        }

        if(!rateType.equals(LoanInvestment.FIXED))
        {
            throw new IllegalArgumentException("The rate type provided is unknown, must be [fixed] ");
        }
    }
}
