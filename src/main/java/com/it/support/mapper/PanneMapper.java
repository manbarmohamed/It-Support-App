package com.it.support.mapper;

import com.it.support.model.Panne;
import com.it.support.dto.PanneDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PanneMapper {
    Panne toEntity(PanneDto panneDto);

    PanneDto toDto(Panne panne);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Panne partialUpdate(PanneDto panneDto, @MappingTarget Panne panne);
}
