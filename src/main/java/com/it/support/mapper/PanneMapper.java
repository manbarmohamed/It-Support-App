package com.it.support.mapper;

import com.it.support.model.Panne;
import com.it.support.dto.PanneDto;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Panne entities and PanneDto objects.
 * Utilizes MapStruct to automatically generate implementation code at compile time.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PanneMapper {

    /**
     * Converts a PanneDto object to a Panne entity.
     *
     * @param panneDto the data transfer object to convert
     * @return the converted Panne entity
     */
    Panne toEntity(PanneDto panneDto);

    /**
     * Converts a Panne entity to a PanneDto object.
     *
     * @param panne the entity to convert
     * @return the converted PanneDto object
     */
    PanneDto toDto(Panne panne);

    /**
     * Partially updates a Panne entity with values from a PanneDto object.
     * Null properties in PanneDto are ignored during mapping.
     *
     * @param panneDto the data transfer object containing updated fields
     * @param panne the entity to update
     * @return the updated Panne entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Panne partialUpdate(PanneDto panneDto, @MappingTarget Panne panne);
}
