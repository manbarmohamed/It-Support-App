package com.it.support.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link SaveTicketDto}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SaveTicketDto implements Serializable {
    String description;
    Long panne_id;
    Long equipement_id;
}