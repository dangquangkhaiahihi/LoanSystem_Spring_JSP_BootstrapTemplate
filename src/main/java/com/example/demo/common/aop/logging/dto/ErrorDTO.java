package com.example.demo.common.aop.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String requestId;

    private String method;

    private String uri;

    private String query;

    private String body;

    private String code;

    private long latency;

    private String message;

    private String throwName;

    private Object stackTraces;
}


