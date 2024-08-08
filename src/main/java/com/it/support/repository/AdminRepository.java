package com.it.support.repository;

import com.it.support.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on {@link Admin} entities.
 *
 * This interface extends {@link JpaRepository}, providing methods for basic CRUD operations such as
 * saving, deleting, and finding {@link Admin} entities by their ID.
 *
 * @author [Manbar Mohamed]
 * @since [1.O]
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Custom query methods for Admin entities can be defined here if needed.
    // For example:
    // List<Admin> findBySomeCriteria(String criteria);
}
