package com.it.support.repository;

import com.it.support.model.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technicien, Long> {
}
