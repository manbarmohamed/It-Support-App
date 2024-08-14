package com.it.support.controller;


import com.it.support.dto.EquipementDto;
import com.it.support.model.Equipement;
import com.it.support.service.EquipementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class EquipmentController {

    private final EquipementService equipementService;

    @GetMapping("/admin/allEquipment")
    public ResponseEntity<List<Equipement>> getAllEquipements() {
        List<Equipement> equipment = equipementService.findAll();
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("/admin/getEquipmentById/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        Equipement equipement = equipementService.findOne(id);
        return ResponseEntity.ok(equipement);
    }

    @PutMapping("/admin/editEquipment/{id}")
    public ResponseEntity<EquipementDto> updateEquipement(@PathVariable Long id, @RequestBody EquipementDto equipementDto) {
        EquipementDto updatedEquipement = equipementService.update(id, equipementDto);
        return ResponseEntity.ok(updatedEquipement);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/addEq")
    public ResponseEntity<EquipementDto> createEquipement(@RequestBody EquipementDto equipementDto) {
        EquipementDto createdEquipement = equipementService.save(equipementDto);
        return ResponseEntity.ok(createdEquipement);
    }

    @DeleteMapping("/admin/deleteEq/{id}")
    public void deleteEquipement(@PathVariable Long id) {
        equipementService.delete(id);
    }

    @PostMapping("/admin/assignEqToUser/{equipementId}/{userId}")
    public Equipement assignEquipementToUser(@PathVariable("equipementId") Long equipementId, @PathVariable("userId") Long userId) {
        return equipementService.assigneEquipementToUser(equipementId, userId);
    }
}
