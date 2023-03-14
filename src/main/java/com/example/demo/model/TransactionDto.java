package com.example.demo.model;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {
    private Long id;
    private String desc;
    private Float amount;
    private String amountStr;
    private LocalDateTime createdAt;
    private String createdAtStr;

    public void setAmount(Float amount, Boolean type) {
        this.amount = amount;
        if (type) {
            this.amountStr = StringUtils.convertFloatToString(amount);
        } else {
            this.amountStr = "-" + StringUtils.convertFloatToString(amount);
        }
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        if (createdAt != null) {
            String stringValue = Utils.convertLocalDateTimeToddMMyyyWithTime(createdAt);
            this.createdAtStr = stringValue;
        } else {
            this.createdAtStr = "";
        }
    }
}
