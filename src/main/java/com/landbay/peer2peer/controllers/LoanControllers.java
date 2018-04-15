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

    @RequestMapping(value = "/delete/{id}", method= RequestMethod.DELETE)
    public void deleteLoan(@PathVariable("id") long id)
    {
        System.out.println("ID : "+id);
        loanServices.deleteLoan(id);
    }

    /**
     * Validating the input for a loan
     * @param loan
     */
    private void validateLoan(Loan loan)
    {
        long amountRequired = loan.getAmountRequired();
        if(amountRequired <= 0)
        {
            throw new IllegalArgumentException("Loan can not be validated - Amount required is invalid ["+amountRequired+"]");
        }
    }
}
