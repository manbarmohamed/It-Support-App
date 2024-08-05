package com.it.support.model;


import com.it.support.enums.PanneStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private LocalDateTime signalDate;
    @Enumerated(EnumType.STRING)
    private PanneStatus status;
    @ManyToOne
    @JoinColumn(name = "equepement_id")
    private Equipement equipment;

}
