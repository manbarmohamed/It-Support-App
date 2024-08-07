package com.it.support.dto;

import lombok.NoArgsConstructor;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.it.support.model.Ticket}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto implements Serializable {
    String description;
}