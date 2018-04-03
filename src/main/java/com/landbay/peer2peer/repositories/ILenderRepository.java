package com.landbay.peer2peer.repositories;

import com.landbay.peer2peer.entities.Lender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILenderRepository extends JpaRepository<Lender, Long>
{
    @Query(value = " SELECT id FROM Lender WHERE 'name' =:name")
    public long findByName(String name);
}
