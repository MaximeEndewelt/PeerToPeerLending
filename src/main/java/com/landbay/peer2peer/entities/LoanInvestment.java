package com.landbay.peer2peer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;

/**
 * Entity representing a loan investments
 */
@Entity
@Table(name = "loaninvestment")
public class LoanInvestment
{
    public final static double FIXED_RATE = 3.5;
    public final static double TRACKER_RATE = 3.0;
    public final static String FIXED = "fixed";
    public final static String TRACKER = "tracker";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    private long loanId;

    private String lenderName;

    private double amount;

    private int investmentPeriod; //in days

    private double interests;

    private String rateType;

    private LoanInvestment(){}

    public long getLoanId() {
        return loanId;
    }

    public String getLenderName() {
        return lenderName;
    }

    public double getAmount() {
        return amount;
    }

    public String getRateType() {
        return rateType;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getInvestmentPeriod() {
        return investmentPeriod;
    }

    public void setInvestmentPeriod(int investmentPeriod) {
        this.investmentPeriod = investmentPeriod;
    }

    public double getInterests() {
        return interests;
    }

    public void setInterests(double interests) {
        this.interests = interests;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public enum ERateType
    {
        EFixedRate(FIXED, FIXED_RATE),
        ETrackerRate(TRACKER, TRACKER_RATE);

        private double rate;
        private String rateType;

        private static HashMap<String, ERateType> sRateTypes = new HashMap<>();
        static
        {
            for(ERateType rateType : ERateType.values())
            {
                sRateTypes.put(rateType.rateType, rateType);
            }
        }

        ERateType(String rateType, double rate)
        {
            this.rateType = rateType;
            this.rate = rate;
        }

        public static ERateType getFromString(String rateType)
        {
            return sRateTypes.get(rateType);
        }

        public double getRate()
        {
            return rate;
        }

    }
}
