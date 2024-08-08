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

/**
 * Service class responsible for handling operations related to Ticket entities.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    @PersistenceContext
    private EntityManager entityManager;

    private final TicketRepository ticketRepository;
    private final TechnicianRepository technicianRepository;
    private final TicketMapper ticketMapper;

    /**
     * Saves a new ticket in the system.
     *
     * @param ticketDto The TicketDto object containing ticket details.
     * @return The saved TicketDto object.
     */
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setDateCreation(LocalDate.now());
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }

    /**
     * Assigns a ticket to a technician and updates its status to IN_PROGRESS.
     *
     * @param ticket_id     The ID of the ticket to be assigned.
     * @param technician_id The ID of the technician to whom the ticket is assigned.
     * @return The updated Ticket entity.
     * @throws TicketNotFoundException     If the ticket is not found.
     * @throws TechnicienNotFoundException If the technician is not found.
     */
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

    /**
     * Resolves a ticket and updates its status to COMPLETED.
     *
     * @param ticket_id The ID of the ticket to be resolved.
     * @return The updated TicketDto object.
     * @throws TicketNotFoundException If the ticket is not found.
     */
    public TicketDto resolveTicket(Long ticket_id) {
        Ticket ticketResolved = ticketRepository.findById(ticket_id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticketResolved.setStatus(TicketStatus.COMPLETED);
        Ticket updatedTicket = ticketRepository.save(ticketResolved);
        return ticketMapper.toDto(updatedTicket);
    }

    /**
     * Finds all tickets associated with a specific user.
     *
     * @param userId The ID of the user whose tickets are to be retrieved.
     * @return A list of TicketDto objects associated with the user.
     */
    public List<TicketDto> findTicketsByUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return tickets.stream()
                .map(ticketMapper::toDto)
                .toList();
    }

    /**
     * Finds all tickets assigned to a specific technician.
     *
     * @param technicianId The ID of the technician whose tickets are to be retrieved.
     * @return A list of TicketDto objects assigned to the technician.
     */
    public List<TicketDto> findTicketsByTechnician(Long technicianId) {
        List<Ticket> tickets = ticketRepository.findByTechnicienId(technicianId);
        return tickets.stream()
                .map(ticketMapper::toDto)
                .toList();
    }
}