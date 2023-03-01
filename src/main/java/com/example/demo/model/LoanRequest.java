package com.example.demo.model;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class LoanRequest {
    public String fromAmountStr;
    public String toAmountStr;
    public String fromCreatedDateStr;
    public String toCreatedDateStr;
    public String fromDeadlineStr;
    public String toDeadlineStr;

    public Float fromAmount;
    public Float toAmount;
    public LocalDateTime fromCreatedDate;
    public LocalDateTime toCreatedDate;
    public LocalDateTime fromDeadline;
    public LocalDateTime toDeadline;

    public String type;

    public Integer statusInt;
    public Boolean status;

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
                this.fromCreatedDate = now.minus(1,ChronoUnit.MONTHS);
                this.toCreatedDate = now.plus(1, ChronoUnit.DAYS);
            }else {
                if (!StringUtils.isEmpty(fromCreatedDateStr)) {
                    this.fromCreatedDate = Utils.convertyyyyMMddToLocalDateTime(fromCreatedDateStr);
                }
                if (!StringUtils.isEmpty(toCreatedDateStr)) {
                    this.toCreatedDate = Utils.convertyyyyMMddToLocalDateTime(toCreatedDateStr);
                }
            }

            if (!StringUtils.isEmpty(fromDeadlineStr))
                this.fromDeadline = Utils.convertyyyyMMddToLocalDateTime(fromDeadlineStr);
            if (!StringUtils.isEmpty(toDeadlineStr))
                this.toDeadline = Utils.convertyyyyMMddToLocalDateTime(toDeadlineStr);
        } catch (Exception e) {
            throw new Exception("Không parse được ngày.");
        }
        if (statusInt == null) return;
        if (statusInt == 1) {
            this.status = true;
        }
        if (statusInt == 0) {
            this.status = false;
        }
    }
}
