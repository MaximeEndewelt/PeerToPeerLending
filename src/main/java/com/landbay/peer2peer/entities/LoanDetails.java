package com.landbay.peer2peer.entities;

import java.util.List;

public class LoanDetails
{
    private Loan loan;
    private List<LoanInvestment> investments;

    public LoanDetails(Loan loan, List<LoanInvestment> investments)
    {
        this.loan = loan;
        this.investments = investments;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public List<LoanInvestment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<LoanInvestment> investments) {
        this.investments = investments;
    }
}
