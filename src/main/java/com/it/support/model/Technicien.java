package com.it.support.model;


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
   private List<Ticket> tickets;

    @Override
    public String getRole() {
        return "ROLE_TECH";
    }
}
