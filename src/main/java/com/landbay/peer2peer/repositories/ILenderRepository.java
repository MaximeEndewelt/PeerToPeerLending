package com.landbay.peer2peer.repositories;

import com.landbay.peer2peer.entities.Lender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILenderRepository extends JpaRepository<Lender, Long>
{
    @Query(value = " SELECT id FROM Lender l WHERE l.name =:name")
    public Long findByName(@Param(value = "name") String name);
}
