package com.it.support.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.support.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract base class representing a person in the system.
 *
 * This class uses single-table inheritance to differentiate between various types of persons (e.g., Admin, Technicien).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Person {

    /**
     * The unique identifier for a person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The username of the person.
     */
    private String username;

    /**
     * The password of the person.
     */
    @JsonIgnore
    private String password;

    /**
     * The role of the person (e.g., Admin, Technicien).
     */
    @Enumerated(EnumType.STRING)
    private Role role;
}
