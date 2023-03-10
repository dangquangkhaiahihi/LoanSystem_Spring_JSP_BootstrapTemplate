package com.example.demo.model;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class RequestFilterRequest {

    private String fromAmountStr;
    private String toAmountStr;
    private String fromCreatedDateStr;
    private String toCreatedDateStr;

    private Float fromAmount;
    private Float toAmount;
    private LocalDateTime fromCreatedDate;
    private LocalDateTime toCreatedDate;

    private String type;
    private String duration;

    public void validateInput() throws Exception {
        try {
            if (!StringUtils.isEmpty(fromAmountStr)) this.fromAmount = Float.parseFloat(fromAmountStr);
            if (!StringUtils.isEmpty(toAmountStr)) this.toAmount = Float.parseFloat(toAmountStr);
        } catch (Exception e) {
            throw new Exception("Số tiền chỉ được phép nhập số và dấu 1 dấu chấm.");
        }
        try {
            LocalDateTime now = LocalDateTime.now();
            if(StringUtils.isEmpty(fromCreatedDateStr) && StringUtils.isEmpty(toCreatedDateStr)){
                this.fromCreatedDate = now.minus(1, ChronoUnit.MONTHS);
                this.toCreatedDate = now.plus(1, ChronoUnit.DAYS);
            }else {
                if (!StringUtils.isEmpty(fromCreatedDateStr)) {
                    this.fromCreatedDate = Utils.convertyyyyMMddToLocalDateTime(fromCreatedDateStr);
                }
                if (!StringUtils.isEmpty(toCreatedDateStr)) {
                    this.toCreatedDate = Utils.convertyyyyMMddToLocalDateTime(toCreatedDateStr);
                }
            }
        } catch (Exception e) {
            throw new Exception("Không parse được ngày.");
        }
    }
}
