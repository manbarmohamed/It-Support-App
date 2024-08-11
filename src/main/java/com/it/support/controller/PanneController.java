package com.it.support.controller;


import com.it.support.dto.PanneDto;
import com.it.support.service.PanneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PanneController {

    private final PanneService panneService;

    @GetMapping("/admin/allPanne")
    public ResponseEntity<List<PanneDto>> getAllPannes() {
        List<PanneDto> pannes = panneService.findAll();
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
}
