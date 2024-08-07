package com.it.support.repository;

import com.it.support.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByTechnicienId(Long technicienId);
}
