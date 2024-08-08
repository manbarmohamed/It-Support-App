package com.it.support.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.support.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity representing a technician.
 *
 * Inherits common properties from Person and adds specific attributes for technicians.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("TECH")
public class Technicien extends Person {

    /**
     * List of tickets assigned to this technician.
     *
     * This relationship is mapped by the "technicien" field in the Ticket entity.
     */
    @OneToMany(mappedBy = "technicien")
    @JsonIgnore
    private List<Ticket> tickets;

    /**
     * Constructor with parameters for initializing the Technicien object.
     *
     * @param id        The ID of the technician.
     * @param name      The name of the technician.
     * @param username  The username of the technician.
     * @param password  The password of the technician.
     * @param role      The role of the technician.
     */
    public Technicien(Long id, String name, String username, String password, Role role) {
        super(id, name, username, password, role);
        this.setRole(Role.TECH);
    }
}
