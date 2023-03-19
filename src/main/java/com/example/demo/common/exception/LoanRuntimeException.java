package com.example.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRuntimeException extends java.lang.RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public LoanRuntimeException(String code) {
        this.code = code;
    }
}
