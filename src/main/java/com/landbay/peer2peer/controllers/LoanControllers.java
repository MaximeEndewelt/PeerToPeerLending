package com.landbay.peer2peer.controllers;

import com.landbay.peer2peer.entities.Loan;
import com.landbay.peer2peer.services.LoanServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A Rest Controller responsible for creating loans request
 */
@RequestMapping("/loan")
@RestController
public class LoanControllers
{
    @Autowired
    private LoanServices loanServices;

    /**
     * A POST endpoint creating a new loan
     * @param loan
     * @return the loan created, with its ID
     */
    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public ResponseEntity<?> createLoan(@RequestBody  Loan loan)
    {
        try
        {
            validateLoan(loan);
            Loan updatedLoan = loanServices.createLoan(loan);
            return ResponseEntity.status(HttpStatus.OK).body(updatedLoan);
        }
        catch (Exception exception)
        {
            return ExceptionConverter.convertException(exception);
        }

    }

    /**
     * A DELETE endpoint deleting a loan
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteLoan(@PathVariable("id") long id)
    {
        try
        {
            loanServices.deleteLoan(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception)
        {
            return ExceptionConverter.convertException(exception);
        }
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
