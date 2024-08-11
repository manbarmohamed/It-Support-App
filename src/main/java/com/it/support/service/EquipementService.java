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

    /**
     * Retrieves all equipment records from the database and converts them to DTOs.
     *
     * @return List of EquipementDto containing all equipment records.
     */
    public List<Equipement> findAll() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipements;
    }

    /**
     * Finds a single equipment by its ID.
     *
     * @param id The ID of the equipment to retrieve.
     * @return EquipementDto containing the found equipment.
     * @throws EquipementNotFoundException if the equipment is not found.
     */
    public Equipement findOne(Long id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement not found"));
        return equipement;
    }

    /**
     * Saves a new equipment record to the database.
     *
     * @param equipementDto The equipment DTO to be saved.
     * @return EquipementDto containing the saved equipment.
     */
    public EquipementDto save(EquipementDto equipementDto) {
        Equipement equipement = equipementMapper.toEntity(equipementDto);
        equipement.setStatus(EquipementStatus.AVAILABLE);
        Equipement savedEquipement = equipementRepository.save(equipement);
        return equipementMapper.toDto(savedEquipement);
    }

    /**
     * Updates an existing equipment record.
     *
     * @param id The ID of the equipment to update.
     * @param equipementDto The DTO containing updated information.
     * @return EquipementDto containing the updated equipment.
     * @throws EquipementNotFoundException if the equipment is not found.
     */
    public EquipementDto update(Long id, EquipementDto equipementDto) {
        Equipement equipementUpdated = equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement not found"));
        equipementMapper.partialUpdate(equipementDto, equipementUpdated);
        Equipement savedEquipement = equipementRepository.save(equipementUpdated);
        return equipementMapper.toDto(savedEquipement);
    }

    /**
     * Deletes an equipment record from the database.
     *
     * @param id The ID of the equipment to delete.
     * @throws EquipementNotFoundException if the equipment is not found.
     * @throws IllegalStateException if the equipment status is not INACTIVE.
     */
    public void delete(Long id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement not found"));

        if (equipement.getStatus() == EquipementStatus.INACTIVE) {
            equipementRepository.delete(equipement);
        } else {
            throw new IllegalStateException("Seuls les équipements obsolètes ou hors service peuvent être supprimés");
        }
    }

    /**
     * Assigns an equipment to a user and updates its status to ACTIVE.
     *
     * @param equipementId The ID of the equipment to assign.
     * @param userId The ID of the user to assign the equipment to.
     * @return Equipement containing the updated equipment entity.
     * @throws EquipementNotFoundException if the equipment is not found.
     * @throws UserNotFoundException if the user is not found.
     */
    @Transactional
    public Equipement assigneEquipementToUser(Long equipementId, Long userId) {
        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new EquipementNotFoundException("Equipement Not Found!!"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found!!"));
        equipement.setUser(user);
        equipement.setStatus(EquipementStatus.ACTIVE);
        return equipementRepository.save(equipement);
    }
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

