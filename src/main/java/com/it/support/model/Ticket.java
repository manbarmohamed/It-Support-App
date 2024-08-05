package com.it.support.model;


import com.it.support.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreation;
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne
    private Panne panne;
    @ManyToOne
    private Technicien technicien;
    @ManyToOne
    private User user;
}