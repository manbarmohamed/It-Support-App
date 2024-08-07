package com.it.support.service;


import com.it.support.dto.TicketDto;
import com.it.support.enums.TicketStatus;
import com.it.support.exception.TechnicienNotFoundException;
import com.it.support.exception.TicketNotFoundException;

import com.it.support.mapper.TicketMapper;
import com.it.support.model.Technicien;
import com.it.support.model.Ticket;
import com.it.support.repository.TechnicianRepository;
import com.it.support.repository.TicketRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {


    @PersistenceContext
    private EntityManager entityManager;
    private final TicketRepository ticketRepository;
    private final TechnicianRepository technicianRepository;

    private final TicketMapper ticketMapper;

    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setDateCreation(LocalDate.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }

    public Ticket attribuerToTechnician(Long ticket_id, Long technician_id) {
        Ticket ticket = ticketRepository.findById(ticket_id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        Technicien technicien = technicianRepository.findById(technician_id)
                .orElseThrow(() -> new TechnicienNotFoundException("Technician not found"));
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticket.setTechnicien(technicien);
        Ticket updatedTicket = ticketRepository.save(ticket);
        return updatedTicket;
    }

    public TicketDto resolveTicket(Long ticket_id) {
        Ticket ticketResolved = ticketRepository.findById(ticket_id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticketResolved.setStatus(TicketStatus.COMPLETED);
        Ticket updatedTicket = ticketRepository.save(ticketResolved);
        return ticketMapper.toDto(updatedTicket);
    }

    public List<TicketDto> findTicketsByUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return tickets.stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    public List<TicketDto> findTicketsByTechnician(Long technicianId) {
        List<Ticket> tickets = ticketRepository.findByTechnicienId(technicianId);
        return tickets.stream()
                .map(ticketMapper::toDto)
                .toList();
    }



    //    public List<Ticket> findTicketsByUser(Long userId) {
//        QTicket ticket = QTicket.ticket;
//
//        JPAQuery<Ticket> query = new JPAQuery<>(entityManager);
//        return query.from(ticket)
//                .where(ticket.user.id.eq(userId))
//                .fetch();
//    }

//    public List<Ticket> findTicketsByTechnician(Long technicianId) {
//        QTicket ticket = QTicket.ticket;
//
//        JPAQuery<Ticket> query = new JPAQuery<>(entityManager);
//        return query.from(ticket)
//                .where(ticket.technicien.id.eq(technicianId))
//                .fetch();
//    }
}
