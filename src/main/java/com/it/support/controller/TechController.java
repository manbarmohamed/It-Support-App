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
@RequestMapping("api/tech/")
public class TechController {

    private final TicketService ticketService;

    @GetMapping("/ticketByTechnician/{technicianId}")
    public ResponseEntity<List<TicketDto>> getTicketsByTechnician(@PathVariable Long technicianId) {
        List<TicketDto> tickets = ticketService.findTicketsByTechnician(technicianId);
        return ResponseEntity.ok(tickets);
    }
    @PutMapping("/{ticketId}/resolve")
    public ResponseEntity<TicketDto> resolveTicket(@PathVariable Long ticketId) {
        TicketDto resolvedTicket = ticketService.resolveTicket(ticketId);
        return ResponseEntity.ok(resolvedTicket);
    }
}
