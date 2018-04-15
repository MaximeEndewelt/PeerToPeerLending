package com.landbay.peer2peer.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity representing a lender
 */
@Entity
public class Lender
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Lender() { } // JPA only

    public Lender(String name)
    {
        this.name  = name;
    }


}
