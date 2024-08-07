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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private EquipementStatus status;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "equipement")
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "equipement")
    @JsonIgnore
    private List<PanneEquipement> panneEquipements;


}
