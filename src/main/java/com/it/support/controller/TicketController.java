package com.it.support.controller;


import com.it.support.dto.TicketDto;
import com.it.support.model.Ticket;
import com.it.support.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/admin/assignTicketToTech/{ticketId}/{technicianId}")
    public ResponseEntity<Ticket> assignToTechnician(
            @PathVariable Long ticketId, @PathVariable Long technicianId) {
        Ticket updatedTicket = ticketService.attribuerToTechnician(ticketId, technicianId);
        return ResponseEntity.ok(updatedTicket);
    }

    @GetMapping("/tech/ticketByTechnician/{technicianId}")
    public ResponseEntity<List<TicketDto>> getTicketsByTechnician(@PathVariable Long technicianId) {
        List<TicketDto> tickets = ticketService.findTicketsByTechnician(technicianId);
        return ResponseEntity.ok(tickets);
    }
    @PutMapping("/tech/{ticketId}/resolve")
    public ResponseEntity<TicketDto> resolveTicket(@PathVariable Long ticketId) {
        TicketDto resolvedTicket = ticketService.resolveTicket(ticketId);
        return ResponseEntity.ok(resolvedTicket);
    }

    @GetMapping("/user/ticketByUser/{userId}")
    public ResponseEntity<List<TicketDto>> getTicketsByUser(@PathVariable("userId") Long userId) {
        List<TicketDto> tickets = ticketService.findTicketsByUser(userId);
        return ResponseEntity.ok(tickets);
    }
    @PostMapping("/user/saveTicket")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto, Long equipement_id, Long panne_id) {
        TicketDto createdTicket = ticketService.save(ticketDto, equipement_id, panne_id);
        return ResponseEntity.ok(createdTicket);
    }
}
