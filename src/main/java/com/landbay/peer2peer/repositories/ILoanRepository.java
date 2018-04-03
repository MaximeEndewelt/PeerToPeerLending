package com.landbay.peer2peer.repositories;

import com.landbay.peer2peer.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILoanRepository extends JpaRepository<Loan, Long>
{
}
