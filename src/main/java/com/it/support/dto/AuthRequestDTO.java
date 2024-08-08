package com.it.support.dto;

import lombok.*;

/**
 * Data Transfer Object for authentication requests.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO {
    private String username;
    private String password;
}
