package com.example.demo.model;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class LoanRequestAdd {
    private String amountStr;
    private String type;
    private String duration;

    private Float amount;
    public void validateInput() throws Exception {
        if(StringUtils.isEmpty(this.amountStr) || StringUtils.isEmpty(this.type) || StringUtils.isEmpty(this.duration)){
            throw new Exception("Vui lòng nhập hết các trường bắt buộc.");
        }
        try {
            if (!StringUtils.isEmpty(amountStr)) this.amount = Float.parseFloat(amountStr);
        } catch (Exception e) {
            throw new Exception("Số tiền chỉ được phép nhập số và dấu 1 dấu chấm.");
        }
    }
}
