package com.it.support.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PanneEquipementKey implements Serializable {

    @Column(name = "equipement_id")
    private Long equipement_id;
    @Column(name = "panne_id")
    private Long panne_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanneEquipementKey that = (PanneEquipementKey) o;
        return Objects.equals(equipement_id, that.equipement_id) && Objects.equals(panne_id, that.panne_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(equipement_id, panne_id);
    }
}
