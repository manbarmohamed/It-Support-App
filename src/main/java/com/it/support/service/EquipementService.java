package com.it.support.service;


import com.it.support.enums.EquipementStatus;
import com.it.support.model.Equipement;

import com.it.support.model.QEquipement;
import com.it.support.model.User;
import com.it.support.repository.EquipementRepository;
import com.it.support.repository.UserRepository;
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
    private final UserRepository userRepository;

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
        equipment.setStatus(EquipementStatus.AVAILABLE);
        return equipementRepository.save(equipment);
    }
    public Equipement update(Long id ,Equipement equipement) throws Exception {
        Equipement equipementUpdated = equipementRepository.findById(id).orElseThrow(()->new Exception("notfound"));
        equipementUpdated.setNome(equipement.getNome());
        equipementUpdated.setType(equipement.getType());
        return equipementRepository.save(equipementUpdated);
    }

    public void delete(Long id) throws Exception {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));

        if (equipement.getStatus() == EquipementStatus.HORS_SERVICE) {
            equipementRepository.delete(equipement);
        } else {
            throw new IllegalStateException("Seuls les équipements obsolètes ou hors service peuvent être supprimés");
        }
    }

    public Equipement assigneEquipementToUser(Long equipementId, Long userId) {
        Equipement equipement = equipementRepository.findById(equipementId).orElseThrow(()->new RuntimeException("Equipement Not Found!!"));
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found!!"));
        equipement.setUser(user);
        equipement.setStatus(EquipementStatus.ACTIVE);
        return equipementRepository.save(equipement);
    }
}
