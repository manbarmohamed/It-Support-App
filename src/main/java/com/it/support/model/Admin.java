package com.it.support.model;

import com.it.support.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an administrator in the system, extending from {@link Person}.
 *
 * This class is mapped to the "ADMIN" discriminator value in a single table inheritance strategy,
 * indicating that this entity represents an admin user.
 *
 * @since 1.0
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Person {

    /**
     * Constructs an {@link Admin} with the specified parameters.
     *
     * @param id the ID of the admin
     * @param name the name of the admin
     * @param username the username of the admin
     * @param password the password of the admin
     * @param role the role of the admin (should be Role.ADMIN)
     */
    public Admin(Long id, String name, String username, String password, Role role) {
        super(id, name, username, password, role);
        this.setRole(Role.ADMIN); // Ensure the role is set to ADMIN
    }

    /**
     * Default constructor for JPA.
     */
    public Admin() {
        super();
        this.setRole(Role.ADMIN); // Ensure the role is set to ADMIN
    }
}
