package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PersonService;
import com.example.demo.service.dto.PersonDTO;
import com.example.demo.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    private final MessageSource messageSource;

    private final PersonMapper personMapper;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUpdate(PersonDTO personDTO) {
        validateCreateUpdateRequest(personDTO);
        enrichPersonInfo(personDTO);
        Optional<UserEntity> userOpt = userRepository.findById(personDTO.getUser().getId());
        PersonEntity person = personMapper.toEntity(personDTO);
        person.setUser(userOpt.get());
        if (Objects.isNull(person.getId())) {
            personRepository.save(person.getName(), person.getAddress(),
                    person.getPhone(), person.getEmail(),
                    person.getTotalAmount(), person.getUser().getId(),
                    person.getCreatedBy(), person.getCreatedDate(),
                    person.getLastModifiedBy(), Instant.now());
        } else {
            personRepository.updatePersonInfo(person.getId(), person.getName(),
                    person.getAddress(), person.getEmail(), person.getPhone());
        }
    }

    private void enrichPersonInfo(PersonDTO personDTO) {
        String currentUser = Utils.auditorAware().getCurrentAuditor().orElse("SYSTEM");
        personDTO.setLastModifiedBy(currentUser);
        personDTO.setCreatedBy(StringUtils.isEmpty(personDTO.getCreatedBy()) ? currentUser : personDTO.getCreatedBy());
        personDTO.setCreatedDate(Objects.isNull(personDTO.getCreatedDate()) ? Instant.now() : personDTO.getCreatedDate());
    }

    @Override
    public Page<PersonEntity> findList(String name, String address, String phone, String email,
                                       Long userId, Long fromId, Long toId, Long fromTotal, Long toTotal,
                                       Instant fromCreatedDate, Instant toCreatedDate,
                                       Instant fromLastModifiedDate, Instant toLastModifiedDate,
                                       Pageable pageable) {
        address = StringUtils.isEmpty(address) ? null : "%" + address.toUpperCase() + "%";
        phone = StringUtils.isEmpty(phone) ? null : "%" + phone.toUpperCase() + "%";
        email = StringUtils.isEmpty(email) ? null : "%" + email.toUpperCase() + "%";
        name = StringUtils.isEmpty(name) ? null : "%" + name.toUpperCase() + "%";
        Page<PersonEntity> page = personRepository.findList(
                address, phone, email, name,
                userId, fromId, toId, fromTotal, toTotal,
                fromCreatedDate, toCreatedDate, fromLastModifiedDate, toLastModifiedDate,
                pageable);
        return page;
    }

    private void validateCreateUpdateRequest(PersonDTO personDTO) {
        Boolean isNotDuplicate = validateDuplicate(personDTO);
        if (Boolean.FALSE.equals(isNotDuplicate)) {
            String message = messageSource.getMessage("create.person.duplicate.phone.number", null, null);
            throw new BusinessException(null, message);
        }
    }

    private Boolean validateDuplicate(PersonDTO personDTO) {
        Optional<PersonEntity> personOpt = personRepository.findByPhone(personDTO.getPhone());
        if (personOpt.isPresent()) {
//            personDTO.setCreatedDate(personOpt.get().getCreatedDate());
            if (Objects.nonNull(personDTO.getId())
                    && personDTO.getId().equals(personOpt.get().getId())) {
                personDTO.setCreatedDate(personOpt.get().getCreatedDate());
                personDTO.setCreatedBy(personOpt.get().getCreatedBy());
                return true;
            }
            return false;
        }
//        personDTO.setCreatedDate(Instant.now());
        return true;
    }
}
