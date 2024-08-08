package com.it.support.repository;

import com.it.support.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on {@link Person} entities.
 *
 * This interface extends {@link JpaRepository}, providing methods for basic CRUD operations such as
 * saving, deleting, and finding {@link Person} entities by their ID.
 *
 * Additionally, it includes a custom query method to find a {@link Person} entity by its username.
 *
 * @since 1.0
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Finds a {@link Person} entity by its username.
     *
     * @param username the username of the person
     * @return the {@link Person} entity with the given username, or null if not found
     */
    Person findByUsername(String username);
}
