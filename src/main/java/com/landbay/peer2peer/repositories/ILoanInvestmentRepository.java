package com.landbay.peer2peer.repositories;

import com.landbay.peer2peer.entities.LoanInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILoanInvestmentRepository extends JpaRepository<LoanInvestment, Long>
{
    @Query(value = " SELECT * FROM LoanInvestment WHERE 'loan_id' =:loanId", nativeQuery = true)
    public List<LoanInvestment> findAllByLoanId(long loanId);

    @Query(" DELETE FROM LoanInvestment WHERE 'loan_id' = :loanId")
    public void deleteByLoanId(long loanId);
}