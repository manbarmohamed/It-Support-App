package com.it.support.controller;

import com.it.support.model.Equipement;
import com.it.support.model.QEquipement;
import com.it.support.service.EquipementService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/eq/")
public class EquipementController {
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
}
