package com.example.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends LoanRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private List<?> data;
    private List<?> fieldErrorDTOS;

    public BusinessException(String code) {
        this.code = code;
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code, List<?> data) {
        this.code = code;
        this.data = data;
    }

    public BusinessException(String code, List<?> data, List<?> fieldErrorDTOS) {
        this.code = code;
        this.data = data;
        this.fieldErrorDTOS = fieldErrorDTOS;
    }

}
