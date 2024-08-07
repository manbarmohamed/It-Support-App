package com.it.support.controller;


import com.it.support.enums.TicketStatus;
import com.it.support.model.Panne;
import com.it.support.model.PanneEquipement;
import com.it.support.model.Ticket;
import com.it.support.service.PanneEquipementService;
import com.it.support.service.PanneService;
import com.it.support.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/")
public class UserController {
    private final PanneEquipementService panneEquipementService;
    private final TicketService ticketService;

//    @PostMapping("/equipement/{equipementId}")
//    public ResponseEntity<Panne> signalerPanne(@PathVariable Long equipementId, @RequestBody Panne panne) {
//        return ResponseEntity.ok(panneService.signalerPanne(equipementId, panne));
//    }

    @PostMapping("signal/{panne_id}/{equipement_id}")
    public PanneEquipement signalerPanne(@PathVariable Long panne_id, @PathVariable Long equipement_id) {
        return panneEquipementService.signalPanne(panne_id, equipement_id);
    }
    @GetMapping("findTicketByUser/{user_id}")
    public List<Ticket> findTicketByUser(@PathVariable Long user_id) {
        return ticketService.findTicketsByUser(user_id);
    }
    @PostMapping("saveTicket")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        ticket.setStatus(TicketStatus.PENDING);
        return ticketService.save(ticket);
    }
}
