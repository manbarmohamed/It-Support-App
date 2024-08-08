package com.it.support.mapper;

import com.it.support.dto.EquipementDto;
import com.it.support.model.Equipement;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Equipement entities and EquipementDto objects.
 * Utilizes MapStruct to automatically generate implementation code at compile time.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipementMapper {

    /**
     * Converts an EquipementDto object to an Equipement entity.
     *
     * @param equipementDto the data transfer object to convert
     * @return the converted Equipement entity
     */
    Equipement toEntity(EquipementDto equipementDto);

    /**
     * Converts an Equipement entity to an EquipementDto object.
     *
     * @param equipement the entity to convert
     * @return the converted EquipementDto object
     */
    EquipementDto toDto(Equipement equipement);

    /**
     * Partially updates an Equipement entity with values from an EquipementDto object.
     * Null properties in EquipementDto are ignored during mapping.
     *
     * @param equipementDto the data transfer object containing updated fields
     * @param equipement the entity to update
     * @return the updated Equipement entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Equipement partialUpdate(EquipementDto equipementDto, @MappingTarget Equipement equipement);
}
