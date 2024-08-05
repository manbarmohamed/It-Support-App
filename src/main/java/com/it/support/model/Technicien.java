package com.it.support.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.support.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@DiscriminatorValue("TECH")
public class Technicien extends Person {

   @OneToMany(mappedBy = "technicien")
   @JsonIgnore
   private List<Ticket> tickets;

    public Technicien(Long id, String name, String username, String password, Role role) {
        super(id, name, username, password, role);
        this.setRole(Role.TECH);
    }
}
