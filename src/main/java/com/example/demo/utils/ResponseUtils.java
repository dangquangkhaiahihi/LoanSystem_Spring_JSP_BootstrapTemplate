package com.example.demo.utils;

import com.example.demo.common.exception.LoanRuntimeException;
import com.example.demo.service.dto.MetadataDTO;
import com.example.demo.service.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static final String CODE_OK = "200";
    public static final String MESSAGE_OK = "Success";
    public static final String MESSAGE_ERROR = "Error";

    public static final String CODE_BAD_REQUEST = "400";
    public static final String CODE_FORBIDDEN = "403";

    public static final String CODE_INTERNAL_ERROR = "500";

    public static ResponseDTO buildResponse(String code, String message, Object data) {
        MetadataDTO meta = new MetadataDTO(code, message);
        return new ResponseDTO(meta, data);
    }

    public static ResponseDTO responseOK(Object data) {
        return buildResponse(CODE_OK, MESSAGE_OK, data);
    }

    public static ResponseDTO responseBadRequest(String message) {
        return buildResponse(CODE_BAD_REQUEST, message, null);
    }

    public static ResponseDTO responseBadRequest(String message, Object data) {
        return buildResponse(CODE_BAD_REQUEST, message, data);
    }

    public static ResponseDTO responseForbidden(String message) {
        return buildResponse(CODE_FORBIDDEN, message, null);
    }

    public static ResponseDTO buildError(MetadataDTO metaData) {
        return new ResponseDTO(metaData, null);
    }

    public static ResponseDTO buildError(String code, String message) {
        return new ResponseDTO(new MetadataDTO(code, message), null);
    }

    public static ResponseEntity<ResponseDTO> response(ResponseDTO responseDTO) {
        return ResponseEntity.ok().body(responseDTO);
    }

    public static ResponseDTO buildError(LoanRuntimeException e) {
        return new ResponseDTO(new MetadataDTO(e.getCode(), e.getMessage()), null);
    }
}
