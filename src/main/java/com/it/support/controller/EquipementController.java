package com.it.support.controller;

import com.it.support.model.Equipement;
import com.it.support.model.QEquipement;
import com.it.support.service.EquipementService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
