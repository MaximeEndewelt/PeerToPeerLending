package com.landbay.peer2peer.controllers;

import com.landbay.peer2peer.entities.LoanDetails;
import com.landbay.peer2peer.entities.LoanInvestment;
import com.landbay.peer2peer.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/investment")
@RestController
public class InvestmentController
{

    @Autowired
    private LoanServices loanServices;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public LoanDetails getLoanDetails(@PathVariable("id") long id)
    {
        LoanDetails loanDetails = null;
        try
        {
            loanDetails = loanServices.getLoan(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return loanDetails;
    }

    @RequestMapping(method= RequestMethod.POST)
    public void investIntoLoan(@RequestBody LoanInvestment loanInvestment)
    {
        System.out.println("Lender name :" +loanInvestment.getLenderName());
        System.out.println("Loan id :" +loanInvestment.getLoanId());
        System.out.println("Amount :" +loanInvestment.getAmount());
        System.out.println("Rate type :" +loanInvestment.getRateType());

        try
        {
            validateInvestment(loanInvestment);
            loanServices.investIntoLoan(loanInvestment);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

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
