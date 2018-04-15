package com.landbay.peer2peer.repositories;

import com.landbay.peer2peer.entities.LoanInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ILoanInvestmentRepository extends JpaRepository<LoanInvestment, Long>
{
//    @Query(value = " SELECT l.amount, l.lenderName, l.loanId, l.interests, l.rateType  FROM LoanInvestment l WHERE l.loanId = :loanId")
    @Query(value = " SELECT l  FROM LoanInvestment l WHERE l.loanId = :loanId")
    public List<LoanInvestment> findAllByLoanId(@Param("loanId") long loanId);

    @Transactional
    @Modifying
    @Query(" DELETE FROM LoanInvestment l WHERE l.loanId = :loanId")
    public void deleteByLoanId(@Param("loanId") long loanId);
}
