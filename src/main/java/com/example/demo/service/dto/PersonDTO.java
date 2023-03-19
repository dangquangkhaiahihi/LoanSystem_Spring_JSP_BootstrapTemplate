package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    private Long id;

    @NotEmpty(message = "Vui lòng không để trống họ tên")
    private String name;

    private String address;

    @NotEmpty(message = "Vui lòng không để trống số điện thoại")
    private String phone;

    private String email;

    private Long totalAmount;

    private UserDto user;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate = Instant.now();

    private Long fromTotal;
    private Long toTotal;
    private Long fromId;
    private Long toId;
    private Instant fromCreatedDate;
    private Instant toCreatedDate;
    private Instant fromLastModifiedDate;
    private Instant toLastModifiedDate;
}
