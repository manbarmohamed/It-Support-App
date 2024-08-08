package com.it.support.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a panne (failure or issue) entity in the system.
 *
 * This class holds information about the panne, including its name and associated tickets and equipment records.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Panne {

    /**
     * The unique identifier for the panne.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name or description of the panne.
     */
    private String nom;

    /**
     * The list of tickets associated with this panne.
     */
    @OneToMany(mappedBy = "panne", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ticket> tickets;

    /**
     * The list of equipment records associated with this panne.
     */
    @OneToMany(mappedBy = "panne", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PanneEquipement> panneEquipements;
}
