package com.it.support.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PanneEquipement {

    @EmbeddedId
    PanneEquipementKey id;

    @ManyToOne
    @MapsId("equipement_id")
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;

    @ManyToOne
    @MapsId("panne_id")
    @JoinColumn(name = "panne_id")
    private Panne panne;


}
