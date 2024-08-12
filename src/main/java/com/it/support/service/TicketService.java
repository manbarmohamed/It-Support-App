package com.it.support.service;

import com.it.support.dto.SaveTicketDto;
import com.it.support.dto.SaveTicketDtoMapper;
import com.it.support.dto.TicketDto;
import com.it.support.enums.EquipementStatus;
import com.it.support.enums.TicketStatus;
import com.it.support.exception.TechnicienNotFoundException;
import com.it.support.exception.TicketNotFoundException;
import com.it.support.mapper.TicketMapper;
import com.it.support.model.*;
import com.it.support.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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



    private final TicketRepository ticketRepository;
    private final TechnicianRepository technicianRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final PanneRepository panneRepository;
    private final EquipementRepository equipementRepository;


    /**
     * Saves a new ticket in the system.
     *
     //* @param ticketDto The TicketDto object containing ticket details.
     * @return The saved TicketDto object.
     */
//    public TicketDto save(TicketDto ticketDto, Long equipement_id, Long panne_id) {
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(loggedInUser.getName());
//        Equipement equipement = equipementRepository.findById(equipement_id).orElseThrow(()-> new EquipementNotFoundException("Equipment not found"));
//        Panne panne = panneRepository.findById(panne_id).orElseThrow(()-> new PanneNotFoundException("Panne not found"));
//        Ticket ticket = ticketMapper.toEntity(ticketDto);
//        ticket.setUser(user);
//        ticket.setEquipement(equipement);
//        ticket.setPanne(panne);
//        ticket.setStatus(TicketStatus.PENDING);
//        ticket.setDateCreation(LocalDate.now());
//        Ticket savedTicket = ticketRepository.save(ticket);
//        return ticketMapper.toDto(savedTicket);
//    }

    public String saveTicket(SaveTicketDto saveTicketDto){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
       User user = userRepository.findByUsername(loggedInUser.getName());

       Equipement equipement = equipementRepository.findById(saveTicketDto.getEquipement_id()).orElse(null);
        System.out.println(equipement+"++++++++++++++++++");
       Panne panne = panneRepository.findById(saveTicketDto.getPanne_id()).orElse(null);
       Ticket ticket=new Ticket();
       ticket.setDescription(saveTicketDto.getDescription());
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setDateCreation(LocalDate.now());
       ticket.setEquipement(equipement);
       ticket.setPanne(panne);
       ticket.setUser(user);
       ticketRepository.save(ticket);
       return "save Seccessfuly";


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
    @Transactional
    public void resolveTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Set the ticket status to RESOLVED
        ticket.setStatus(TicketStatus.COMPLETED);
        ticketRepository.save(ticket);

        // Set the equipment status to ACTIVE if associated equipment exists
        Equipement equipement = ticket.getEquipement();
        if (equipement != null) {
            equipement.setStatus(EquipementStatus.ACTIVE);
            equipementRepository.save(equipement);
        }
    }

    /**
     * Finds all tickets associated with a specific user.
     *
     * @return A list of TicketDto objects associated with the user.
     */
    public List<Ticket> findTicketsByUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(loggedInUser.getName());
        List<Ticket> tickets = ticketRepository.findAllByUserId(user.getId());
        return tickets;
    }

    /**
     * Finds all tickets assigned to a specific technician.
     *
     * @param technicianId The ID of the technician whose tickets are to be retrieved.
     * @return A list of TicketDto objects assigned to the technician.
     */
    public List<Ticket> findTicketsByTechnician(Long technicianId) {
        List<Ticket> tickets = ticketRepository.findAllByTechnicienId(technicianId);
        return tickets;
    }

    public Ticket findTicketById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new TicketNotFoundException("Ticket not found"));
        return ticket;
    }

    public List<Ticket> findTicketsByUserId(Long userId) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(userId);
        return tickets;
    }

}