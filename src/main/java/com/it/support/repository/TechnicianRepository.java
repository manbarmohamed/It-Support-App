package com.it.support.repository;

import com.it.support.model.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on {@link Technicien} entities.
 *
 * This interface extends {@link JpaRepository}, providing methods for basic CRUD operations such as
 * saving, deleting, and finding {@link Technicien} entities by their ID.
 *
 * @since 1.0
 */
@Repository
public interface TechnicianRepository extends JpaRepository<Technicien, Long> {
    // Additional query methods can be defined here if needed.
    // For example:
    // List<Technicien> findBySpecialization(String specialization);
}
