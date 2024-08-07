package com.it.support.repository;

import com.it.support.model.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PanneRepository extends JpaRepository<Panne,Long> {
}
