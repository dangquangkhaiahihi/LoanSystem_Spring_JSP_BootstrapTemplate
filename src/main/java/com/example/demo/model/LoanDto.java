package com.example.demo.model;

import com.example.demo.common.Constant;
import com.example.demo.common.StringUtils;
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

    private Boolean canLoan = true;

    public void setAmount(Float amount) {
        this.amount = StringUtils.convertFloatToString(amount);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        String stringValue = Utils.convertLocalDateTimeToddMMyyy(createdAt);
        this.createdAt = stringValue;
    }

    public void setDuration(String duration) {
        if (Constant.DURATION_ONE_YEAR.equals(duration)) {
            this.duration = "1 năm";
        } else if (Constant.DURATION_ONE_MONTH.equals(duration)) {
            this.duration = "1 tháng";
        } else if (Constant.DURATION_TWO_MONTHS.equals(duration)) {
            this.duration = "2 tháng";
        } else if (Constant.DURATION_THREE_MONTHS.equals(duration)) {
            this.duration = "3 tháng";
        }
    }

}
