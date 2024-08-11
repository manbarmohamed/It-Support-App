package com.it.support.repository;

import com.it.support.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link Ticket} entities.
 *
 * This interface extends {@link JpaRepository}, providing methods for basic CRUD operations such as
 * saving, deleting, and finding {@link Ticket} entities by their ID.
 *
 * Additionally, it includes custom query methods for finding tickets by user ID and technician ID.
 *
 * @since 1.0
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Finds all {@link Ticket} entities associated with a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of {@link Ticket} entities associated with the given user ID
     */
    List<Ticket> findAllByUserId(Long userId);

    /**
     * Finds all {@link Ticket} entities associated with a specific technician ID.
     *
     * @param technicienId the ID of the technician
     * @return a list of {@link Ticket} entities associated with the given technician ID
     */
    List<Ticket> findAllByTechnicienId(Long technicienId);
}
