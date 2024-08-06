package com.it.support.service;


import com.it.support.model.Panne;
import com.it.support.model.QPanne;
import com.it.support.repository.PanneRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class PanneService {

    private final PanneRepository panneRepository;

    public Panne save(Panne panne) {
        return panneRepository.save(panne);
    }
    public List<Panne> findAll() {
        QPanne panne = QPanne.panne;
        BooleanExpression predicate = panne.isNotNull();
        return (List<Panne>) panneRepository.findAll(predicate);
    }
    public List<Panne> findPanneEquipement(Long equipementId) {
        QPanne panne = QPanne.panne;
        BooleanExpression predicate = panne.equipment.id.eq(equipementId);
        return (List<Panne>) panneRepository.findAll(predicate);
    }

}
