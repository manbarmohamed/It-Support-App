package com.it.support.mapper;

import com.it.support.dto.EquipementDto;
import com.it.support.model.Equipement;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipementMapper {
    Equipement toEntity(EquipementDto equipementDto1);

    EquipementDto toDto(Equipement equipement);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Equipement partialUpdate(EquipementDto equipementDto1, @MappingTarget Equipement equipement);
}
