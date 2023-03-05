package com.example.demo.model;

import com.example.demo.common.Utils;
import com.example.demo.entity.TraceUserLoanEntity;
import com.example.demo.entity.UserEntity;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class LoanDto {
    private Long id;
    private String amount;
    private String createdAt;
    private String type;
    private String duration;
    private Boolean status;
    private UserEntity user;
    private List<TraceUserLoanEntity> traces = new ArrayList<>();
    private String interestStr;
    private Float interest;

    public void setAmount(Float amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String stringValue = decimalFormat.format(amount);
        this.amount = stringValue;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        String stringValue = Utils.convertLocalDateTimeToddMMyyy(createdAt);
        this.createdAt = stringValue;
    }
}
