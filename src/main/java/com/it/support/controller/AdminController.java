package com.it.support.controller;


import com.it.support.model.Equipement;
import com.it.support.service.EquipementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final EquipementService equipementService;


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

    @PostMapping("addEq")
    public Equipement addEquipement(@RequestBody Equipement equipement) throws Exception {
        return equipementService.save(equipement);
    }
    @DeleteMapping("deleteEq/{id}")
    public void deleteEquipement(@PathVariable Long id) throws Exception {
        equipementService.delete(id);
    }
    @PostMapping("assigne/{user_id}/{equipement_id}")
    public Equipement assigneEquipement(@PathVariable String user_id, @PathVariable String equipement_id) throws Exception {
        return equipementService.assigneEquipementToUser(Long.valueOf(user_id),Long.valueOf(equipement_id));

    }
}
