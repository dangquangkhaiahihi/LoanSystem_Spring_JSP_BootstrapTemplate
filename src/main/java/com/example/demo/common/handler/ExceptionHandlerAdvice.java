package com.example.demo.common.handler;

import com.example.demo.common.Utils;
import com.example.demo.common.aop.logging.dto.ErrorDTO;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.exception.LoanRuntimeException;
import com.example.demo.service.dto.ResponseDTO;
import com.example.demo.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

import static com.example.demo.constant.Constant.COMMON.REQUEST_ID;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, BusinessException e) {
        // handle exception
        buildInfoLog(request, e);
        ResponseDTO responseDTO = getResponseDTO(request, e.getCode(), e.getMessage(), e.getData());
        if (CollectionUtils.isNotEmpty(e.getFieldErrorDTOS())) {
            responseDTO.setData(e.getFieldErrorDTOS());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(responseDTO);

    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handle(HttpServletRequest request, BindException e) {
        buildErrorLog(request, String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage(), e.getStackTrace(), e.getClass().getName());
        FieldError fieldError = e.getFieldError();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(createErrorBody(request, String.valueOf(HttpStatus.BAD_REQUEST.value()), Objects.nonNull(fieldError) ? fieldError.getDefaultMessage() : "Unknown"));
    }

    private void buildErrorLog(HttpServletRequest request, String code, String message, StackTraceElement[] stackTrace, String throwName) {

        log.error(Utils.convertObjectToJsonString(buildLog(request, code, message, stackTrace, throwName)));
    }

    private ResponseDTO getResponseDTO(HttpServletRequest request, String code, String message, List<?> data) {
        return createErrorBody(request, code, message);
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, String error, String message) {
        return createErrorBody(request, ResponseUtils.buildError(error, message));
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, LoanRuntimeException e) {
        return createErrorBody(request, ResponseUtils.buildError(e));
    }

    private ResponseDTO createErrorBody(HttpServletRequest request, ResponseDTO body) {

        if (body.getMeta() != null) {
            String message = body.getMeta().getMessage();
            if (message == null) {
                message = "";
            }
            String requestId = getAttributeAsString(request, REQUEST_ID);
            if (requestId != null) {
                String fiveLastChar;
                if (requestId.length() > 5) {
                    fiveLastChar = requestId.substring(requestId.length() - 5);
                } else {
                    fiveLastChar = requestId;
                }

                message = message + String.format("(%s)", fiveLastChar);
            }
            body.getMeta().setMessage(message);

        }
        return body;
    }

    private void buildInfoLog(HttpServletRequest request, LoanRuntimeException e) {
        buildInfoLog(request, e.getCode(), e.getMessage(), e.getStackTrace());
    }

    private void buildInfoLog(HttpServletRequest request, String code, String message, StackTraceElement[] stackTrace) {
        log.info(Utils.convertObjectToJsonString(buildLog(request, code, message, stackTrace, null)));
    }

    private ErrorDTO buildLog(HttpServletRequest request, String code, String message,
                              StackTraceElement[] stackTrace, String throwName) {
        String requestId = getAttributeAsString(request, REQUEST_ID);
        ErrorDTO dto = new ErrorDTO();
        dto.setRequestId(requestId);
        dto.setMethod(request.getMethod());
        dto.setUri(request.getRequestURI());
        dto.setQuery(request.getQueryString());
//        dto.setBody(getBody(request));
        dto.setCode(code);
        dto.setMessage(message);
        dto.setThrowName(throwName);
        dto.setStackTraces(stackTrace);
        return dto;
    }

    public static String getAttributeAsString(HttpServletRequest request, String key) {
        return (String) request.getAttribute(key);
    }
}
