package com.it.support.service;



import com.it.support.dto.PanneDto;
import com.it.support.exception.PanneNotFoundException;
import com.it.support.mapper.PanneMapper;
import com.it.support.model.Panne;

import com.it.support.repository.EquipementRepository;
import com.it.support.repository.PanneRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PanneService {

    private final PanneMapper panneMapper;
    private final PanneRepository panneRepository;


    public PanneDto save(PanneDto panneDto) {
        Panne panne = panneMapper.toEntity(panneDto);
        Panne savedPanne = panneRepository.save(panne);
        return panneMapper.toDto(savedPanne);
    }

    public List<PanneDto> findAll() {
        List<Panne> pannes = panneRepository.findAll();
        return pannes.stream()
                .map(panneMapper::toDto)
                .toList();
    }

    public void delete(Long id) {
        panneRepository.deleteById(id);
    }

    public PanneDto updatePanne(Long id, PanneDto panneDto) {
        Panne panneUpdated = panneRepository.findById(id)
                .orElseThrow(() -> new PanneNotFoundException("Panne not found"));
        panneMapper.partialUpdate(panneDto, panneUpdated);
        Panne savedPanne = panneRepository.save(panneUpdated);
        return panneMapper.toDto(savedPanne);
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

//    public List<Panne> findAll() {
//        QPanne panne = QPanne.panne;
//        BooleanExpression predicate = panne.isNotNull();
//        return (List<Panne>) panneRepository.findAll(predicate);
//    }
}
