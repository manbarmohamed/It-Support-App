package com.it.support.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.support.enums.EquipementStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an equipment entity in the system.
 *
 * This class holds information about the equipment, including its status, associated user, tickets, and panne records.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipement {

    /**
     * The unique identifier for the equipment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the equipment.
     */
    private String nome;

    /**
     * The status of the equipment.
     */
    @Enumerated(EnumType.STRING)
    private EquipementStatus status;

    /**
     * The user to whom the equipment is assigned.
     */
    @ManyToOne
    @JsonIgnore
    private User user;

    /**
     * The list of tickets associated with this equipment.
     */
    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();

    /**
     * The list of panne records associated with this equipment.
     */
    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PanneEquipement> panneEquipements = new ArrayList<>();
}
