package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Data
public class TicketDTO {
    private Long id;

    private Long personId;

    private Long amount;

    private Boolean isPlus;

    private String note;

    private Instant dateOfTrans;
    private String dateOfTransStr;


    ////////////////
    private Long personIdTicketAdd;
    private Long amountTicketAdd;

    private Boolean isPlusTicketAdd;

    private String noteTicketAdd;

    private String dateTransTicketAddStr;

    private MultipartFile imgTicketAdd;

    public void validateRequest () throws Exception {
        if(this.amountTicketAdd == null || this.isPlusTicketAdd == null){
            throw new Exception("Vui lòng không bỏ trống các trường bắt buộc");
        }

        if(StringUtils.isEmpty(this.dateTransTicketAddStr)){
            this.dateOfTrans = Instant.now();
        }else{
            this.dateOfTransStr = this.dateTransTicketAddStr;
        }

        mapFERequestToObject();
    }

    private void mapFERequestToObject () {
        if(!StringUtils.isEmpty(this.dateOfTransStr)){
            this.dateOfTrans = Instant.parse(this.dateOfTransStr+ ":00Z");
        }
        this.amount = this.amountTicketAdd;
        this.isPlus = this.isPlusTicketAdd;
        this.note = this.noteTicketAdd;
        this.personId = this.personIdTicketAdd;
    }
}
