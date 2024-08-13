package com.it.support.controller;


import com.it.support.dto.SaveTicketDto;
import com.it.support.dto.TicketDto;
import com.it.support.model.Ticket;
import com.it.support.repository.TicketRepository;
import com.it.support.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;

    @PostMapping("/admin/assignTicketToTech/{ticketId}/{technicianId}")
    public ResponseEntity<Ticket> assignToTechnician(
            @PathVariable Long ticketId, @PathVariable Long technicianId) {
        Ticket updatedTicket = ticketService.attribuerToTechnician(ticketId, technicianId);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/tech/ticketByTechnician")
    public ResponseEntity<List<Ticket>> getTicketsByTechnician() {
        List<Ticket> tickets = ticketService.findTicketsByTechnician();
        return ResponseEntity.ok(tickets);
    }
    @PutMapping("/tech/{ticketId}/resolve")
    public void resolveTicket(@PathVariable Long ticketId) {
       ticketService.resolveTicket(ticketId);
    }

    @GetMapping("/user/ticketByUser")
    public ResponseEntity<List<Ticket>> getTicketsByUser() {
        List<Ticket> tickets = ticketService.findTicketsByUser();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/user/allTicket")
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ResponseEntity.ok(tickets);
    }

//    @PostMapping("/user/saveTicket")
//    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto, Long equipement_id, Long panne_id) {
//        TicketDto createdTicket = ticketService.save(ticketDto, equipement_id, panne_id);
//        return ResponseEntity.ok(createdTicket);
//    }

    @GetMapping("/user/findById/{ticket_id}")
    public Ticket findById(@PathVariable("ticket_id") Long ticketId) {
        return ticketService.findTicketById(ticketId);
    }


    @PostMapping("/user/save")
    public String saveTicket(@RequestBody SaveTicketDto ticketDto) {
        return ticketService.saveTicket(ticketDto);
    }

    @GetMapping("/user/ticketByUserId/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Long userId) {
        List<Ticket> tickets = ticketService.findTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }
}
