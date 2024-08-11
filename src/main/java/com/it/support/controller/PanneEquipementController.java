package com.it.support.controller;


import com.it.support.model.PanneEquipement;
import com.it.support.service.PanneEquipementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PanneEquipementController {
    private final PanneEquipementService panneEquipementService;

    @PostMapping("/user/signal/{panne_id}/{equipement_id}")
    public PanneEquipement signalerPanne(@PathVariable("panne_id") Long panne_id, @PathVariable("equipement_id") Long equipement_id) {
        return panneEquipementService.signalPanne(panne_id, equipement_id);
    }
    @GetMapping("/admin/historique/{id}")
    public List<PanneEquipement> historiqueEquipement(@PathVariable Long id){
        return panneEquipementService.findAllByEquipementId(id);
    }
}
