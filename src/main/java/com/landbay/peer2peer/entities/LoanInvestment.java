package com.landbay.peer2peer.entities;

import javax.persistence.*;

@Entity
@Table(name = "loaninvestment")
public class LoanInvestment
{
    @Id
    @GeneratedValue
    private long id;

    private long loanId;

    private long lenderId;

    @Transient
    private String lenderName;

    private double amount;

    private double rate;

    @Transient
    public String getLenderName() {
        return lenderName;
    }
}
