package com.it.support.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("USER")
public class User extends Person{

    @OneToMany
    private List<Panne> pannes;
    @OneToMany
    private List<Ticket> tickets;


    @Override
    public String getRole() {
        return "ROLE_USER";
    }
}
