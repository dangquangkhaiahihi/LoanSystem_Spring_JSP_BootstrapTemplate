package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataDTO {

    private String code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    public MetadataDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
