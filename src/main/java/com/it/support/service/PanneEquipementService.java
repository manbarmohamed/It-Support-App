package com.it.support.service;

import com.it.support.enums.EquipementStatus;
import com.it.support.exception.EquipementNotFoundException;
import com.it.support.exception.PanneNotFoundException;
import com.it.support.model.Equipement;
import com.it.support.model.Panne;
import com.it.support.model.PanneEquipement;
import com.it.support.model.PanneEquipementKey;
import com.it.support.repository.EquipementRepository;
import com.it.support.repository.PanneEquipementRepository;
import com.it.support.repository.PanneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanneEquipementService {

    private final PanneRepository panneRepository;
    private final EquipementRepository equipementRepository;
    private final PanneEquipementRepository panneEquipementRepository;

    /**
     * Retrieves all failures (pannes) associated with a specific equipment ID.
     *
     * @param equipementId The ID of the equipment for which to retrieve pannes.
     * @return A list of PanneEquipement objects representing the pannes associated with the given equipment.
     */
    public List<PanneEquipement> findAllByEquipementId(Long equipementId) {
        return panneEquipementRepository.findAllByEquipementId(equipementId);
    }

    /**
     * Signals a panne (failure) for a given equipment and panne ID, updating the equipment's status and creating a panne-equipment association.
     *
     * @param panneId     The ID of the panne to signal.
     * @param equipementId The ID of the equipment experiencing the panne.
     * @return A PanneEquipement object representing the newly created panne-equipment association.
     * @throws PanneNotFoundException       if the panne is not found.
     * @throws EquipementNotFoundException if the equipment is not found.
     */
    public PanneEquipement signalPanne(Long panneId, Long equipementId) {
        Panne panne = panneRepository.findById(panneId).orElseThrow(() -> new PanneNotFoundException("Panne not found"));
        Equipement equipement = equipementRepository.findById(equipementId).orElseThrow(() -> new EquipementNotFoundException("Equipment not found"));
        equipement.setStatus(EquipementStatus.PANE);
        equipementRepository.save(equipement);
        var signal = new PanneEquipement(new PanneEquipementKey(equipement.getId(), panne.getId()), equipement, panne);
        return panneEquipementRepository.save(signal);
    }
}
