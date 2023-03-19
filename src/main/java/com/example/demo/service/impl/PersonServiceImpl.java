package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<PersonEntity> findList(String name, String address, String phone, String email,
                                       Long userId, Long fromId, Long toId, Long fromTotal, Long toTotal,
                                       Instant fromCreatedDate, Instant toCreatedDate,
                                       Instant fromLastModifiedDate, Instant toLastModifiedDate) {
        address = StringUtils.isEmpty(address) ? null : "%" + address.toUpperCase() + "%";
        phone = StringUtils.isEmpty(phone) ? null : "%" + phone.toUpperCase() + "%";
        email = StringUtils.isEmpty(email) ? null : "%" + email.toUpperCase() + "%";
        name = StringUtils.isEmpty(name) ? null : "%" + name.toUpperCase() + "%";
        List<PersonEntity> list = personRepository.findList(
                address, phone, email, name,
                userId, fromId, toId, fromTotal, toTotal,
                fromCreatedDate, toCreatedDate, fromLastModifiedDate, toLastModifiedDate);
        return list;
    }
}
