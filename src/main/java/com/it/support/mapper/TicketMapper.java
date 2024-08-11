package com.it.support.mapper;

import com.it.support.dto.TicketDto;
import com.it.support.model.Ticket;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper interface for converting between Ticket entities and TicketDto objects.
 * Uses MapStruct for generating mapping code at compile time.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    /**
     * Converts a TicketDto object to a Ticket entity.
     *
     * @param ticketDto the data transfer object to convert
     * @return the converted Ticket entity
     */
    Ticket toEntity(TicketDto ticketDto);

    List<Ticket> toEntity(List<TicketDto> ticketDto);


    /**
     * Converts a Ticket entity to a TicketDto object.
     *
     * @param ticket the entity to convert
     * @return the converted TicketDto object
     */
    TicketDto toDto(Ticket ticket);

    List<TicketDto> toDto(List<Ticket> ticket);

    /**
     * Partially updates a Ticket entity with values from a TicketDto object.
     * Null properties in TicketDto are ignored during mapping.
     *
     * @param ticketDto the data transfer object containing updated fields
     * @param ticket the entity to update
     * @return the updated Ticket entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Ticket partialUpdate(TicketDto ticketDto, @MappingTarget Ticket ticket);
}
