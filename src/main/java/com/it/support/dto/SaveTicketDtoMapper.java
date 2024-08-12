package com.it.support.dto;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SaveTicketDtoMapper {
    SaveTicketDto toEntity(SaveTicketDto saveTicketDto);

    SaveTicketDto toDto(SaveTicketDto saveTicketDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SaveTicketDto partialUpdate(SaveTicketDto saveTicketDtoDto, @MappingTarget SaveTicketDto saveTicketDto);
}