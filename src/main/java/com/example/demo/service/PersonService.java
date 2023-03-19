package com.example.demo.service;

import com.example.demo.entity.PersonEntity;
import com.example.demo.service.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface PersonService {
    void createUpdate(PersonDTO personDTO);

    Page<PersonEntity> findList(String name, String address, String phone, String email,
                                Long userId, Long fromId, Long toId, Long fromTotal, Long toTotal,
                                Instant fromCreatedDate, Instant toCreatedDate,
                                Instant fromLastModifiedDate, Instant toLastModifiedDate,
                                Pageable pageable);

}
