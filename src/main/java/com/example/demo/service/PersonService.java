package com.example.demo.service;

import com.example.demo.entity.PersonEntity;
import com.example.demo.model.PersonDTO;

import java.time.Instant;
import java.util.List;

public interface PersonService {
    List<PersonEntity> findList(String name, String address, String phone, String email,
                                Long userId, Long fromId, Long toId, Long fromTotal, Long toTotal,
                                Instant fromCreatedDate, Instant toCreatedDate,
                                Instant fromLastModifiedDate, Instant toLastModifiedDate);

    void createUpdate(PersonDTO personDTO) throws Exception;
}
