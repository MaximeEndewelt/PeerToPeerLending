package com.landbay.peer2peer.controllers;

import com.landbay.peer2peer.entities.Lender;
import com.landbay.peer2peer.entities.Loan;
import com.landbay.peer2peer.entities.LoanInvestment;
import com.landbay.peer2peer.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/loan")
@RestController
public class LoanControllers
{
    @Autowired
    private LoanServices loanServices;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public Loan createLoan(@RequestBody  Loan loan)
    {
        validateLoan(loan);
        return loanServices.createLoan(loan);
    }

    @RequestMapping(value = "/delete", method= RequestMethod.DELETE)
    public void deleteLoan(@PathVariable long id)
    {
        loanServices.deleteLoan(id);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<LoanInvestment> getLoan(@PathVariable long id)
    {
        return loanServices.getLoan(id);
    }

    @RequestMapping(value = "/invest", method= RequestMethod.POST)
    public void investIntoLoan(LoanInvestment loanInvestment)
    {
        loanServices.investIntoLoan(loanInvestment);
    }




    private void validateLoan(Loan loan)
    {
        // Do something
    }
}
