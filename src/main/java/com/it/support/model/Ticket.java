package com.it.support.model;

import com.it.support.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity representing a support ticket.
 *
 * Contains details about the ticket, its status, and related entities.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    /**
     * Unique identifier for the ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date when the ticket was created.
     */
    private LocalDate dateCreation;

    /**
     * Description of the issue or request in the ticket.
     */
    private String description;

    /**
     * Current status of the ticket.
     */
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    /**
     * Technician assigned to the ticket.
     *
     * This is a many-to-one relationship with the Technicien entity.
     */
    @ManyToOne
    @JoinColumn(name = "tech_id")
    private Technicien technicien;

    /**
     * User who reported the issue or created the ticket.
     *
     * This is a many-to-one relationship with the User entity.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Equipment related to the ticket.
     *
     * This is a many-to-one relationship with the Equipement entity.
     */
    @ManyToOne
    private Equipement equipement;

    /**
     * Issue or fault related to the ticket.
     *
     * This is a many-to-one relationship with the Panne entity.
     */
    @ManyToOne
    private Panne panne;
}
