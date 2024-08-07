package com.it.support.dto;

import lombok.NoArgsConstructor;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.it.support.model.Equipement}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipementDto implements Serializable {
    String nome;
}