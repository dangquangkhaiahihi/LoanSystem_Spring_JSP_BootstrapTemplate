package com.example.demo.model;

import com.example.demo.common.StringUtils;
import lombok.Data;

import java.time.Instant;

@Data
public class PersonFilterRequest {
    String name;
    String address;
    String phone;
    String email;
    Long userId;
    Long fromId;
    Long toId;
    Long fromTotal;
    Long toTotal;

    String fromCreatedDateStr;
    String toCreatedDateStr;
    String fromLastModifiedDateStr;
    String toLastModifiedDateStr;
    Instant fromCreatedDate;
    Instant toCreatedDate;
    Instant fromLastModifiedDate;
    Instant toLastModifiedDate;

    public void stringToInstant () {
        if(!StringUtils.isEmpty(fromCreatedDateStr)){
            this.fromCreatedDate = Instant.parse(fromCreatedDateStr+ ":00Z");
        }
        if(!StringUtils.isEmpty(toCreatedDateStr)){
            this.toCreatedDate = Instant.parse(toCreatedDateStr+ ":00Z");
        }
        if(!StringUtils.isEmpty(fromLastModifiedDateStr)){
            this.fromLastModifiedDate = Instant.parse(fromLastModifiedDateStr+ ":00Z");
        }
        if(!StringUtils.isEmpty(toLastModifiedDateStr)){
            this.toLastModifiedDate = Instant.parse(toLastModifiedDateStr+ ":00Z");
        }
    }
}
