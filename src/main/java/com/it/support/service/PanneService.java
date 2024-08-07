package com.it.support.service;


import com.it.support.exception.EquipementNotFoundException;
import com.it.support.exception.PanneNotFoundException;
import com.it.support.model.Panne;
import com.it.support.model.QPanne;
import com.it.support.repository.EquipementRepository;
import com.it.support.repository.PanneRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PanneService {

    private final PanneRepository panneRepository;
    private final EquipementRepository equipementRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Panne save(Panne panne) {
        return panneRepository.save(panne);
    }
    public List<Panne> findAll() {
        QPanne panne = QPanne.panne;
        BooleanExpression predicate = panne.isNotNull();
        return (List<Panne>) panneRepository.findAll(predicate);
    }

    public void delete(Long id) {
        panneRepository.deleteById(id);
    }

    public Panne updatePanne(Long id,Panne panne) {
        Panne panneUpdated = panneRepository.findById(id).orElseThrow(()->new PanneNotFoundException("Panne not found"));;
        panneUpdated.setNom(panne.getNom());
        return panneRepository.save(panneUpdated);
    }


//    public Panne signalerPanne(Long equipementId, Panne panne) {
//        Equipement equipement = equipementRepository.findById(equipementId)
//                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));
//        panne.setEquipements((List<Equipement>) equipement);
//        panne.setSignalDate(LocalDateTime.now());
//        panne.setStatus(PanneStatus.pa);
//        return panneRepository.save(panne);
//    }

//    public List<Panne> findPannesByStatus(PanneStatus status) {
//        QPanne panne = QPanne.panne;
//        JPAQuery<Panne> query = new JPAQuery<>(entityManager);
//        return query.from(panne)
//                .where(panne.status.eq(status))
//                .fetch();
//    }
}
