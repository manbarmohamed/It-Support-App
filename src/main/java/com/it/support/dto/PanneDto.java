package com.it.support.dto;

import com.it.support.model.Panne;
import lombok.NoArgsConstructor;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Panne}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PanneDto implements Serializable {
    String nom;
}