package com.it.support.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.support.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("USER")
public class User extends Person{

    @OneToMany
    @JsonIgnore
    private List<Panne> pannes;
    @OneToMany
    @JsonIgnore
    private List<Ticket> tickets;
    @OneToMany
    @JsonIgnore
    private List<Equipement> equipements;

    public User(Long id, String name, String username, String password, Role role) {
        super(id, name, username, password, role);
        this.setRole(Role.USER);
    }
}
