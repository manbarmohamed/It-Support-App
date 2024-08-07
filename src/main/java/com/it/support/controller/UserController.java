package com.it.support.controller;


import com.it.support.model.Panne;
import com.it.support.model.PanneEquipement;
import com.it.support.service.PanneEquipementService;
import com.it.support.service.PanneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final PanneEquipementService panneEquipementService;

//    @PostMapping("/equipement/{equipementId}")
//    public ResponseEntity<Panne> signalerPanne(@PathVariable Long equipementId, @RequestBody Panne panne) {
//        return ResponseEntity.ok(panneService.signalerPanne(equipementId, panne));
//    }

    @PostMapping("signal/{panne_id}/{equipement_id}")
    public PanneEquipement signalerPanne(@PathVariable Long panne_id, @PathVariable Long equipement_id) {
        return panneEquipementService.signalPanne(panne_id, equipement_id);
    }
}
