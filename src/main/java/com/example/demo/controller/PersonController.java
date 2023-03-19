package com.example.demo.controller;

import com.example.demo.entity.PersonEntity;
import com.example.demo.service.PersonService;
import com.example.demo.service.dto.PersonDTO;
import com.example.demo.service.dto.ResponseDTO;
import com.example.demo.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity<ResponseDTO> createUpdate(@Valid @RequestBody PersonDTO personDTO) {
        personService.createUpdate(personDTO);
        return ResponseEntity.ok().body(ResponseUtils.responseOK(null));
    }

    @GetMapping(value = "/find")
    @ResponseBody
    public ResponseEntity<ResponseDTO> findList(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "address", required = false) String address,
                                                @RequestParam(value = "phone", required = false) String phone,
                                                @RequestParam(value = "email", required = false) String email,
                                                @RequestParam(value = "userId") Long userId,
                                                @RequestParam(value = "fromId", required = false) Long fromId,
                                                @RequestParam(value = "toId", required = false) Long toId,
                                                @RequestParam(value = "fromTotal", required = false) Long fromTotal,
                                                @RequestParam(value = "toTotal", required = false) Long toTotal,
                                                @RequestParam(value = "fromCreatedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime fromCreatedDate,
                                                @RequestParam(value = "toCreatedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime toCreatedDate,
                                                @RequestParam(value = "fromLastModifiedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime fromLastModifiedDate,
                                                @RequestParam(value = "toLastModifiedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime toLastModifiedDate,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Instant fromCreatedDateInstant = Objects.nonNull(fromCreatedDate) ? fromCreatedDate.toInstant() : null;
        Instant toCreatedDateInstant = Objects.nonNull(toCreatedDate) ? toCreatedDate.toInstant() : null;
        Instant fromLastModifiedDateInstant = Objects.nonNull(fromLastModifiedDate) ? fromLastModifiedDate.toInstant() : null;
        Instant toLastModifiedDateInstant = Objects.nonNull(toLastModifiedDate) ? toLastModifiedDate.toInstant() : null;
        Page<PersonEntity> pageResult = personService.findList(name, address, phone, email, userId,
                fromId, toId, fromTotal, toTotal,
                fromCreatedDateInstant, toCreatedDateInstant,
                fromLastModifiedDateInstant, toLastModifiedDateInstant,
                PageRequest.of(page, size));
        ResponseDTO response = ResponseUtils.responseOK(pageResult.toList());
        response.getMeta().setTotal(pageResult.getTotalElements());
        response.getMeta().setPage(pageResult.getNumber());
        response.getMeta().setSize(pageResult.getSize());

        return ResponseEntity.ok().body(response);
    }
}
