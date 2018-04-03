package com.landbay.peer2peer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Loan
{
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Column(name = "amount_required")
    private long amountRequired;

    private double amountInvested;

    private double totalRepayment;

    private Loan() { } // JPA only

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
