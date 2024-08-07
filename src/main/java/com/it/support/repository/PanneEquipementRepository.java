package com.it.support.repository;


import com.it.support.model.PanneEquipement;
import com.it.support.model.PanneEquipementKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface PanneEquipementRepository extends JpaRepository<PanneEquipement, PanneEquipementKey> {
    List<PanneEquipement> findAllByEquipementId(Long equipmentId);
}
