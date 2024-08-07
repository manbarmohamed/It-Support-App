package com.it.support.mapper;

import com.it.support.dto.TicketDto;
import com.it.support.model.Ticket;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    Ticket toEntity(TicketDto ticketDto);

    TicketDto toDto(Ticket ticket);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ticket partialUpdate(TicketDto ticketDto, @MappingTarget Ticket ticket);
}
