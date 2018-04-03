package com.landbay.peer2peer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lender
{
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private Lender() { } // JPA only

    public Lender(String name)
    {
        this.name  = name;
    }


}
