package com.it.support.service;


import com.it.support.enums.EquipementStatus;
import com.it.support.exception.EquipementNotFoundException;
import com.it.support.exception.UserNotFoundException;
import com.it.support.model.Equipement;

import com.it.support.model.QEquipement;
import com.it.support.model.QPanne;
import com.it.support.model.User;
import com.it.support.repository.EquipementRepository;
import com.it.support.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipementService {

//    @PersistenceContext
//    private EntityManager entityManager;
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
        return equipementRepository.findOne(predicate).orElseThrow(()->new EquipementNotFoundException("equipement not found"));
    }

    public Equipement save(Equipement equipment) {
        equipment.setStatus(EquipementStatus.AVAILABLE);

        return equipementRepository.save(equipment);
    }
    public Equipement update(Long id ,Equipement equipement) throws Exception {
        Equipement equipementUpdated = equipementRepository.findById(id).orElseThrow(()->new EquipementNotFoundException("equipement not found"));
        equipementUpdated.setNome(equipement.getNome());
        return equipementRepository.save(equipementUpdated);
    }

    public void delete(Long id) throws Exception {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement not found"));

        if (equipement.getStatus() == EquipementStatus.INACTIVE) {
            equipementRepository.delete(equipement);
        } else {
            throw new IllegalStateException("Seuls les équipements obsolètes ou hors service peuvent être supprimés");
        }
    }

    @Transactional
    public Equipement assigneEquipementToUser(Long equipementId, Long userId) {
        Equipement equipement = equipementRepository.findById(equipementId).orElseThrow(()-> new EquipementNotFoundException("Equipement Not Found!!"));
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found!!"));
        equipement.setUser(user);
        equipement.setStatus(EquipementStatus.ACTIVE);
        return equipementRepository.save(equipement);
    }

//    public List<Equipement> findEquipementsWithPannes() {
//        QEquipement equipement = QEquipement.equipement;
//        QPanne panne = QPanne.panne;
//
//        JPAQuery<Equipement> query = new JPAQuery<>(entityManager);
//        return query.from(equipement)
//                .leftJoin(equipement.pannes, panne)
//                .fetchJoin()
//                .distinct()
//                .fetch();
//    }
}
