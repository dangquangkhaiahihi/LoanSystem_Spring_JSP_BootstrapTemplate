package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;

@Data
public class TicketFilterRequest {
    Boolean isPlus;
    String note;
    Long id;
    Long personId;
    Long fromAmount;
    Long toAmount;
    Instant fromLastModifiedDate;
    Instant toLastModifiedDate;
    Instant fromDateOfTrans;
    Instant toDateOfTrans;
    String fromLastModifiedDateStr;
    String toLastModifiedDateStr;
    String fromDateOfTransStr;
    String toDateOfTransStr;


    Integer isPlusTicket;
    String noteTicket;
    Long idTicket;
    Long personIdTicket;
    Long fromAmountTicket;
    Long toAmountTicket;
    String fromLastModifiedDateStrTicket;
    String toLastModifiedDateStrTicket;
    String fromDateOfTransStrTicket;
    String toDateOfTransStrTicket;

    public void stringToInstant () {
        if(!StringUtils.isEmpty(fromLastModifiedDateStr)){
            this.fromLastModifiedDate = Instant.parse(fromLastModifiedDateStr+ ":00Z");
        }
        if(!StringUtils.isEmpty(toLastModifiedDateStr)){
            this.toLastModifiedDate = Instant.parse(toLastModifiedDateStr+ ":00Z");
        }
        if(!StringUtils.isEmpty(fromDateOfTransStr)){
            this.fromDateOfTrans = Instant.parse(fromDateOfTransStr+ ":00Z");
        }
        if(!StringUtils.isEmpty(toDateOfTransStr)){
            this.toDateOfTrans = Instant.parse(toDateOfTransStr+ ":00Z");
        }
    }

    public void mapFERequestToObject () {
        if(this.isPlusTicket != null){
            if(this.isPlusTicket == 0){
                this.isPlus = null;
            } else if(this.isPlusTicket == 1){
                this.isPlus = true;
            } else if(this.isPlusTicket == 2){
                this.isPlus = false;
            }
        }
        if(this.personId == null) this.personId = this.personIdTicket;

        this.note = this.noteTicket;
        this.id = this.idTicket;
        this.fromAmount = this.fromAmountTicket;
        this.toAmount = this.toAmountTicket;
        this.fromLastModifiedDateStr = this.fromLastModifiedDateStrTicket;
        this.toLastModifiedDateStr = this.toLastModifiedDateStrTicket;
        this.fromDateOfTransStr = this.fromDateOfTransStrTicket;
        this.toDateOfTransStr = this.toDateOfTransStrTicket;
    }
}
