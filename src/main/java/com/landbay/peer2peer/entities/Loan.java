package com.landbay.peer2peer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Entity representing a loan
 */
@Entity
public class Loan
{
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount_required")
    private long amountRequired;

    private double amountInvested;

    private double totalRepayment;

    private Loan() { } // JPA only

    public long getId() {
        return id;
    }

    public long getAmountRequired() {
        return amountRequired;
    }

    public double getAmountInvested() {
        return amountInvested;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }

    public void setAmountRequired(long amountRequired) {
        this.amountRequired = amountRequired;
    }

    public void setAmountInvested(double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public void setTotalRepayment(double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }
}
