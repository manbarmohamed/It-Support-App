package com.it.support.repository;

import com.it.support.model.PanneEquipement;
import com.it.support.model.PanneEquipementKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link PanneEquipement} entities.
 *
 * This interface extends {@link JpaRepository}, providing methods for basic CRUD operations such as
 * saving, deleting, and finding {@link PanneEquipement} entities by their composite key {@link PanneEquipementKey}.
 *
 * Additionally, it includes a custom query method to find all {@link PanneEquipement} entities by equipment ID.
 *
 * @since 1.0
 */
@Repository
public interface PanneEquipementRepository extends JpaRepository<PanneEquipement, PanneEquipementKey> {

    /**
     * Finds all {@link PanneEquipement} entities associated with a given equipment ID.
     *
     * @param equipmentId the ID of the equipment
     * @return a list of {@link PanneEquipement} entities
     */
    List<PanneEquipement> findAllByEquipementId(Long equipmentId);
}
