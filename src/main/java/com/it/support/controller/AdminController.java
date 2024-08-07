package com.it.support.controller;


import com.it.support.dto.*;
import com.it.support.model.*;
import com.it.support.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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



    @GetMapping("allEquipment")
    public ResponseEntity<List<EquipementDto>> getAllEquipements() {
        List<EquipementDto> equipements = equipementService.findAll();
        return ResponseEntity.ok(equipements);
    }
    @GetMapping("getEquipmentById/{id}")
    public ResponseEntity<EquipementDto> getEquipementById(@PathVariable Long id) {
        EquipementDto equipement = equipementService.findOne(id);
        return ResponseEntity.ok(equipement);
    }
    @PutMapping("editEquipment/{id}")
    public ResponseEntity<EquipementDto> updateEquipement(@PathVariable Long id, @RequestBody EquipementDto equipementDto) {
        EquipementDto updatedEquipement = equipementService.update(id, equipementDto);
        return ResponseEntity.ok(updatedEquipement);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("addEq")
    public ResponseEntity<EquipementDto> createEquipement(@RequestBody EquipementDto equipementDto) {
        EquipementDto createdEquipement = equipementService.save(equipementDto);
        return ResponseEntity.ok(createdEquipement);
    }
    @DeleteMapping("deleteEq/{id}")
    public void deleteEquipement(@PathVariable Long id){
        equipementService.delete(id);
    }
    @PostMapping("assignEqToUser/{equipementId}/{userId}")
    public Equipement assignEquipementToUser(@PathVariable Long equipementId, @PathVariable Long userId) {
        return equipementService.assigneEquipementToUser(equipementId, userId);
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
    public ResponseEntity<List<PanneDto>> getAllPannes() {
        List<PanneDto> pannes = panneService.findAll();
        return ResponseEntity.ok(pannes);
    }
    @PostMapping("savePanne")
    public ResponseEntity<PanneDto> createPanne(@RequestBody PanneDto panneDto) {
        PanneDto createdPanne = panneService.save(panneDto);
        return ResponseEntity.ok(createdPanne);
    }
    @PutMapping("editPanne/{id}")
    public ResponseEntity<PanneDto> updatePanne(@PathVariable Long id, @RequestBody PanneDto panneDto) {
        PanneDto updatedPanne = panneService.updatePanne(id, panneDto);
        return ResponseEntity.ok(updatedPanne);
    }
    @DeleteMapping("delPanne/{id}")
    public void deletePanne(@PathVariable Long id)  {
        panneService.delete(id);
    }
    //Historique
    @GetMapping("historique/{id}")
    public List<PanneEquipement> historiqueEquipement(@PathVariable Long id){
        return panneEquipementService.findAllByEquipementId(id);
    }

    //ticket
    @PostMapping("assignTicketToTech/{ticketId}/{technicianId}")
    public ResponseEntity<Ticket> assignToTechnician(
            @PathVariable Long ticketId, @PathVariable Long technicianId) {
        Ticket updatedTicket = ticketService.attribuerToTechnician(ticketId, technicianId);
        return ResponseEntity.ok(updatedTicket);
    }

}
