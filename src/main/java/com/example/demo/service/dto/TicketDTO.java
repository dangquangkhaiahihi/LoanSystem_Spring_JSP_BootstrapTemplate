package com.example.demo.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {
    private Long id;

    @NotNull(message = "Vui lòng không để trống số tiền")
    private Long amount;

    @NotNull(message = "Vui lòng không để trống loại nợ")
    private Boolean isPlus;

    private String note;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "Asia/Ho_Chi_Minh")
    private Instant dateOfTrans;

    private String imgUrl;

    private PersonDTO person;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;
}
