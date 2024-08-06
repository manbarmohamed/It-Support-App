package com.it.support.service;


import com.it.support.enums.EquipementStatus;
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

    public Equipement findOne(Long id) {
        QEquipement qe = QEquipement.equipement;
        BooleanExpression predicate = qe.id.eq(id);
        return equipementRepository.findOne(predicate).orElse(null);
    }

    public Equipement save(Equipement equipment) {
        return equipementRepository.save(equipment);
    }
    public Equipement update(Long id ,Equipement equipement) throws Exception {
        Equipement equipementUpdated = equipementRepository.findById(id).orElseThrow(()->new Exception("notfound"));
        equipementUpdated.setNome(equipement.getNome());
        equipementUpdated.setType(equipement.getType());
        return equipementRepository.save(equipementUpdated);
    }

    public void delete(Long id) throws Exception {
        Equipement equipement = equipementRepository.findById(id).orElse(null);

        if(equipement!=null && equipement.getStatus()== EquipementStatus.HORS_SERVICE || equipement.getStatus()== EquipementStatus.ABSOLUTE){
            equipementRepository.delete(equipement);
        }
        throw new Exception("Equipement is active");


    }
}
