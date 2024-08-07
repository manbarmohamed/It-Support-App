package com.it.support.service;


import com.it.support.enums.TicketStatus;
import com.it.support.exception.TechnicienNotFoundException;
import com.it.support.exception.TicketNotFoundException;
import com.it.support.model.QTicket;
import com.it.support.model.Technicien;
import com.it.support.model.Ticket;
import com.it.support.repository.TechnicianRepository;
import com.it.support.repository.TicketRepository;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {


    @PersistenceContext
    private EntityManager entityManager;
    private final TicketRepository ticketRepository;
    private final TechnicianRepository technicianRepository;

    public Ticket save(Ticket ticket) {
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setDateCreation(LocalDate.now());
        return ticketRepository.save(ticket);
    }

    public Ticket attribuerToTechnician(Long ticket_id, Long technician_id) {
        Ticket ticket = ticketRepository.findById(ticket_id).orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        Technicien technicien = technicianRepository.findById(technician_id).orElseThrow(() -> new TechnicienNotFoundException("Technician not found"));

        ticket.setTechnicien(technicien);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> findTicketsByUserAndStatus(Long userId, TicketStatus status) {
        QTicket ticket = QTicket.ticket;

        JPAQuery<Ticket> query = new JPAQuery<>(entityManager);
        return query.from(ticket)
                .where(ticket.user.id.eq(userId)
                        .and(ticket.status.eq(status)))
                .fetch();
    }

    public List<Ticket> findTicketsByTechnician(Long technicianId) {
        QTicket ticket = QTicket.ticket;

        JPAQuery<Ticket> query = new JPAQuery<>(entityManager);
        return query.from(ticket)
                .where(ticket.technicien.id.eq(technicianId))
                .fetch();
    }
}
