package com.it.support.service;


import com.it.support.model.Equipement;

import com.it.support.model.QEquipement;
import com.it.support.repository.EquipementRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementService {
    private final EquipementRepository equipementRepository;

    public List<Equipement> findAll() {
        QEquipement qEquipement = QEquipement.equipement;
        BooleanExpression predicate = qEquipement.isNotNull();
        return (List<Equipement>) equipementRepository.findAll(predicate);
    }
}
