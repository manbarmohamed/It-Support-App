package com.it.support.controller;


import com.it.support.dto.TicketDto;
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
    public PanneEquipement signalerPanne(@PathVariable("panne_id") Long panne_id, @PathVariable("equipement_id") Long equipement_id) {
        return panneEquipementService.signalPanne(panne_id, equipement_id);
    }
    @GetMapping("/ticketByUser/{userId}")
    public ResponseEntity<List<TicketDto>> getTicketsByUser(@PathVariable("userId") Long userId) {
        List<TicketDto> tickets = ticketService.findTicketsByUser(userId);
        return ResponseEntity.ok(tickets);
    }
    @PostMapping("saveTicket")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        TicketDto createdTicket = ticketService.save(ticketDto);
        return ResponseEntity.ok(createdTicket);
    }
}
