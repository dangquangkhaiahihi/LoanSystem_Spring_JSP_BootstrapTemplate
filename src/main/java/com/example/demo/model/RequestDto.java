package com.example.demo.model;

import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDto {
    private Long id;
    private LoanDto loanDto;
    private UserDto debtorDto;
    private String createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        String stringValue = Utils.convertLocalDateTimeToddMMyyy(createdAt);
        this.createdAt = stringValue;
    }

}
