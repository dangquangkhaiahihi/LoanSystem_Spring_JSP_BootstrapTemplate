package com.example.demo.service.mapper;

import com.example.demo.entity.TicketEntity;
import com.example.demo.service.dto.TicketDTO;
import org.mapstruct.Mapper;

import java.util.Objects;

/**
 * Mapper for the entity {@link TicketEntity} and its DTO {@link TicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TicketMapper extends EntityMapper<TicketDTO, TicketEntity> {
    default TicketEntity fromId(Long id) {
        if (Objects.isNull(id))
            return null;
        TicketEntity ticket = new TicketEntity();
        ticket.setId(id);
        return ticket;
    }
}