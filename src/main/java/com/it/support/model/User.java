package com.it.support.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.support.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a user in the support system, extending from Person.
 *
 * Contains additional details and relationships specific to users.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("USER")
public class User extends Person {


    /**
     * List of tickets created by the user.
     *
     * This is a one-to-many relationship with the Ticket entity.
     *
     * @JsonIgnore prevents this field from being serialized into JSON.
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets;

    /**
     * List of equipment owned by the user.
     *
     * This is a one-to-many relationship with the Equipement entity.
     *
     * @JsonIgnore prevents this field from being serialized into JSON.
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Equipement> equipements;

    /**
     * Constructs a new User with specified details.
     *
     * @param id       The user's unique identifier.
     * @param name     The user's name.
     * @param username The user's username.
     * @param password The user's password.
     * @param role     The user's role.
     */
    public User(Long id, String name, String username, String password, Role role) {
        super(id, name, username, password, role);
        this.setRole(Role.USER);
    }
}
