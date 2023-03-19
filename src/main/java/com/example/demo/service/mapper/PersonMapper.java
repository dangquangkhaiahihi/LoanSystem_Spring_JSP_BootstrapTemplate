package com.example.demo.service.mapper;

import com.example.demo.entity.PersonEntity;
import com.example.demo.service.dto.PersonDTO;
import org.mapstruct.Mapper;

import java.util.Objects;

/**
 * Mapper for the entity {@link PersonEntity} and its DTO {@link PersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonMapper extends EntityMapper<PersonDTO, PersonEntity> {
    default PersonEntity fromId(Long id) {
        if (Objects.isNull(id))
            return null;
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(id);
        return personEntity;
    }
}
