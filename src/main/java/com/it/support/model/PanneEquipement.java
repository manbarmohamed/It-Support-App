package com.it.support.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the association between a panne (failure or issue) and equipment.
 *
 * This class maps to a composite key that links `Equipement` and `Panne` entities.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PanneEquipement {

    /**
     * The composite primary key for this association.
     */
    @EmbeddedId
    private PanneEquipementKey id;

    /**
     * The equipment associated with this panne.
     */
    @ManyToOne
    @MapsId("equipementId")
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;

    /**
     * The panne associated with this equipment.
     */
    @ManyToOne
    @MapsId("panneId")
    @JoinColumn(name = "panne_id")
    private Panne panne;
}
