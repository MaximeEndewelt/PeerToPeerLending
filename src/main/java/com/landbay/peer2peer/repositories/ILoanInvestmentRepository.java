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
    @Query(value = " SELECT * FROM LoanInvestment WHERE 'loan_id' =:loanId", nativeQuery = true)
    public List<LoanInvestment> findAllByLoanId(long loanId);

    @Transactional
    @Modifying
    @Query(" DELETE FROM LoanInvestment l WHERE l.loanId = :loanId")
    public void deleteByLoanId(@Param("loanId") long loanId);
}
