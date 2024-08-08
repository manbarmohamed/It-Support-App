package com.it.support.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key class for the PanneEquipement entity.
 *
 * This class represents the primary key consisting of `equipement_id` and `panne_id`.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PanneEquipementKey implements Serializable {

    /**
     * The ID of the equipment.
     */
    @Column(name = "equipement_id")
    private Long equipementId;

    /**
     * The ID of the panne (failure).
     */
    @Column(name = "panne_id")
    private Long panneId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanneEquipementKey that = (PanneEquipementKey) o;
        return Objects.equals(equipementId, that.equipementId) &&
                Objects.equals(panneId, that.panneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipementId, panneId);
    }
}
