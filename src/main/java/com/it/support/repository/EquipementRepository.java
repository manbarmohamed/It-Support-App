package com.it.support.repository;

import com.it.support.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@EnableJpaRepositories(basePackageClasses = EquipementRepository.class)
public interface EquipementRepository extends JpaRepository<Equipement, Long> , QuerydslPredicateExecutor<Equipement> {
}
