package com.it.support.controller;

import com.it.support.model.Ticket;
import com.it.support.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tech/")
public class TechController {

    private final TicketService ticketService;

    @GetMapping("findTicketByTech/{tech_id}")
    public List<Ticket> findTicketByTech(@PathVariable Long tech_id) {
        return ticketService.findTicketsByTechnician(tech_id);
    }
    @PostMapping("resolve/{id}")
    public Ticket resolve(@PathVariable Long id) {
      return ticketService.resolveTicket(id);
    }
}
