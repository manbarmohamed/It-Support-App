package com.it.support.service;


import com.it.support.dto.EquipementDto;
import com.it.support.enums.EquipementStatus;
import com.it.support.exception.EquipementNotFoundException;
import com.it.support.exception.UserNotFoundException;
import com.it.support.mapper.EquipementMapper;
import com.it.support.model.Equipement;


import com.it.support.model.User;
import com.it.support.repository.EquipementRepository;
import com.it.support.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipementService {


    private final EquipementRepository equipementRepository;
    private final UserRepository userRepository;


    private final EquipementMapper equipementMapper;

    public List<EquipementDto> findAll() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipements.stream()
                .map(equipementMapper::toDto)
                .toList();
    }

    public EquipementDto findOne(Long id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement not found"));
        return equipementMapper.toDto(equipement);
    }

    public EquipementDto save(EquipementDto equipementDto) {
        Equipement equipement = equipementMapper.toEntity(equipementDto);
        equipement.setStatus(EquipementStatus.AVAILABLE);
        Equipement savedEquipement = equipementRepository.save(equipement);
        return equipementMapper.toDto(savedEquipement);
    }

    public EquipementDto update(Long id, EquipementDto equipementDto) {
        Equipement equipementUpdated = equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement not found"));
        equipementMapper.partialUpdate(equipementDto, equipementUpdated);
        Equipement savedEquipement = equipementRepository.save(equipementUpdated);
        return equipementMapper.toDto(savedEquipement);
    }

    public void delete(Long id) {
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

   //    public List<Equipement> findAll() {
//        QEquipement qEquipement = QEquipement.equipement;
//        BooleanExpression predicate = qEquipement.isNotNull();
//        return (List<Equipement>) equipementRepository.findAll(predicate);
//    }
//public Equipement findOne(Long id) {
//        QEquipement qe = QEquipement.equipement;
//        BooleanExpression predicate = qe.id.eq(id);
//        return equipementRepository.findOne(predicate).orElseThrow(()->new EquipementNotFoundException("equipement not found"));
//    }

    //    @PersistenceContext
//    private EntityManager entityManager;
}
