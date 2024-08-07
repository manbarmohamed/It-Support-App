package com.it.support.controller;


import com.it.support.model.Panne;
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
    private final PanneService panneService;

//    @PostMapping("/equipement/{equipementId}")
//    public ResponseEntity<Panne> signalerPanne(@PathVariable Long equipementId, @RequestBody Panne panne) {
//        return ResponseEntity.ok(panneService.signalerPanne(equipementId, panne));
//    }
}
