package com.it.support.controller;


import com.it.support.dto.PanneDto;
import com.it.support.dto.SaveTicketDto;
import com.it.support.model.Panne;
import com.it.support.model.Ticket;
import com.it.support.repository.TicketRepository;
import com.it.support.service.PanneService;
import com.it.support.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PanneController {

    private final PanneService panneService;
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;


    @PostMapping("/user/savet")
    public String saveTicket(@RequestBody SaveTicketDto ticketDto) {
        return ticketService.saveTicket(ticketDto);
    }

    @GetMapping("/public/allPanne")
    public ResponseEntity<List<Panne>> getAllPannes() {
        List<Panne> pannes = panneService.findAll();
        return ResponseEntity.ok(pannes);
    }
    @PostMapping("/admin/savePanne")
    public ResponseEntity<PanneDto> createPanne(@RequestBody PanneDto panneDto) {
        PanneDto createdPanne = panneService.save(panneDto);
        return ResponseEntity.ok(createdPanne);
    }
    @PutMapping("/admin/editPanne/{id}")
    public ResponseEntity<PanneDto> updatePanne(@PathVariable Long id, @RequestBody PanneDto panneDto) {
        PanneDto updatedPanne = panneService.updatePanne(id, panneDto);
        return ResponseEntity.ok(updatedPanne);
    }
    @DeleteMapping("/admin/delPanne/{id}")
    public void deletePanne(@PathVariable Long id)  {
        panneService.delete(id);
    }

    @GetMapping("/admin/getById/{id}")
    public PanneDto getPanneById(@PathVariable Long id) {
        return panneService.getPanneById(id);
    }
}
