package com.it.support.controller;


import com.it.support.dto.AuthRequestDTO;
import com.it.support.dto.JwtResponseDTO;
import com.it.support.model.*;
import com.it.support.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/")
public class AdminController {
    private final EquipementService equipementService;
    private final PanneService panneService;
    private final PanneEquipementService panneEquipementService;
    private final TicketService ticketService;
    private final UserAuthService userAuthService;
    private final PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            JwtResponseDTO jwtResponseDTO = userAuthService.login(authRequestDTO);
            return ResponseEntity.ok(jwtResponseDTO);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("addUser")
    //@PreAuthorize("hasRole('ADMIN')")
    public User addUser(@RequestBody User user) {
        return personService.addUser(user);
    }
    @PostMapping("addTech")
    //@PreAuthorize("hasRole('ADMIN')")
    public Technicien addTech(@RequestBody Technicien technicien) {
        return personService.addTech(technicien);
    }
    @PostMapping("addAdmin")
    //@PreAuthorize("hasRole('ADMIN')")
    public Admin addAdmin(@RequestBody Admin admin) {
        return personService.addAdmin(admin);
    }



    @GetMapping("all")
    public List<Equipement> getEquipement() {
        return equipementService.findAll();
    }
    @GetMapping("get/{id}")
    public Equipement getEquipement(@PathVariable Long id) {
        return equipementService.findOne(id);
    }
    @PutMapping("/{id}")
    public Equipement getEquipement(@PathVariable Long id, @RequestBody Equipement equipement) throws Exception {
        return equipementService.update(id,equipement);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("addEq")
    public Equipement addEquipement(@RequestBody Equipement equipement) throws Exception {
        return equipementService.save(equipement);
    }
    @DeleteMapping("deleteEq/{id}")
    public void deleteEquipement(@PathVariable Long id) throws Exception {
        equipementService.delete(id);
    }
    @PostMapping("assigne/{equipement_id}/{user_id}")
    public Equipement assigneEquipement(@PathVariable Long equipement_id, @PathVariable Long user_id ) throws Exception {
        return equipementService.assigneEquipementToUser(equipement_id,user_id);
    }

//    @GetMapping("/with-pannes")
//    public ResponseEntity<List<Equipement>> getEquipementsWithPannes() {
//        return ResponseEntity.ok(equipementService.findEquipementsWithPannes());
//    }
//    @GetMapping("/status/{status}")
//    public ResponseEntity<List<Panne>> getPannesByStatus(@PathVariable PanneStatus status) {
//        return ResponseEntity.ok(panneService.findPannesByStatus(status));
//    }


    //Panne Operation

    @GetMapping("allPanne")
    public List<Panne> getPanne() {
        return panneService.findAll();
    }
    @PostMapping("savePanne")
    public Panne savePanne(@RequestBody Panne panne) throws Exception {
        return panneService.save(panne);
    }
    @PutMapping("editPanne/{id}")
    public Panne editPanne(@PathVariable Long id, @RequestBody Panne panne) throws Exception {
        return panneService.updatePanne(id,panne);
    }
    @DeleteMapping("delPanne/{id}")
    public void deletePanne(@PathVariable Long id) throws Exception {
        panneService.delete(id);
    }
    //Historique
    @GetMapping("historique/{id}")
    public List<PanneEquipement> historiqueEquipement(@PathVariable Long id) throws Exception {
        return panneEquipementService.findAllByEquipementId(id);
    }

    //ticket
    @PostMapping("attribuerTicket/{ticket_id}/{tech_id}")
    public Ticket attribuerTicketToTech(@PathVariable Long ticket_id, @PathVariable Long tech_id) throws Exception {
        return ticketService.attribuerToTechnician(ticket_id,tech_id);
    }

}
