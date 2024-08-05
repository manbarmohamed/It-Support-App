package com.it.support.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity

@DiscriminatorValue("ADMIN")
public class Admin extends Person{
    @Override
    public String getRole() {
        return "ROLE_ADMIN";
    }
}
