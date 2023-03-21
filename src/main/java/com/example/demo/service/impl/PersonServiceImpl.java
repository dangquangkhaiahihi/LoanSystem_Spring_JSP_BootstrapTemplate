package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.PersonDTO;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

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

    @Override
    @Transactional
    public void createUpdate(PersonDTO personDTO) throws Exception {
        //Check dubplicate phone
        PersonEntity person = new PersonEntity();
        UserEntity user = userRepository.findByUsername(Utils.getCurrentUser().getName());

        if (Objects.isNull(personDTO.getId())) {
            //Validate null input
            try {
                personDTO.validateRequestAdd();
            } catch (Exception ex) {
                throw ex;
            }
            //Check dubplicate phone
            person = personRepository.findByPhoneAndUserId(personDTO.getPhone(), user.getId());
            if (person != null) {
                throw new Exception("Số điện thoại đã tồn tại.");
            }
            //Set up entity
            person = new PersonEntity();
            BeanUtils.copyProperties(personDTO, person);
            person.setUser(user);
            person.setCreatedDate(Instant.now());
            person.setLastModifiedDate(Instant.now());
            //Database insert
            personRepository.save(person.getName(), person.getAddress(),
                    person.getPhone(), person.getEmail(),
                    person.getTotalAmount(), person.getUser().getId(),
                    person.getCreatedDate(),
                    person.getLastModifiedDate());
        } else {
            //Validate null input
            try {
                personDTO.validateRequestEdit();
            } catch (Exception ex) {
                throw ex;
            }
            //Check dubplicate phone
            person = personRepository.findByPhoneAndUserId(personDTO.getPhone(), user.getId());
            if (person != null) {
                if (!personDTO.getId().equals(person.getId()) && person.getUser().getId().equals(user.getId()))
                    throw new Exception("Số điện thoại đã tồn tại.");
            }
            //Set up update entity
            person = new PersonEntity();
            BeanUtils.copyProperties(personDTO, person);
            person.setLastModifiedDate(Instant.now());
            //Database update
            personRepository.updatePersonInfo(person.getId(), person.getName(),
                    person.getAddress(), person.getEmail(), person.getPhone(), person.getLastModifiedDate());
        }
    }
}
