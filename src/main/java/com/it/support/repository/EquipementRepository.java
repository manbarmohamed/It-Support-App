package com.it.support.repository;

import com.it.support.enums.EquipementStatus;
import com.it.support.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link Equipement} entities.
 *
 * This interface extends {@link JpaRepository}, providing methods for basic CRUD operations such as
 * saving, deleting, and finding {@link Equipement} entities by their ID.
 *
 * @since 1.0
 */
@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {


    List<Equipement> findByStatus(EquipementStatus status);
}
