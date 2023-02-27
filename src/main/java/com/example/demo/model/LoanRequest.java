package com.example.demo.model;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;

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
    public Boolean status;

    public void validateInput() throws Exception{
        try{
            if(!StringUtils.isEmpty(fromAmountStr)) this.fromAmount = Float.parseFloat(fromAmountStr);
            if(!StringUtils.isEmpty(toAmountStr)) this.toAmount = Float.parseFloat(toAmountStr);
        }catch (Exception e){
            throw new Exception("Số tiền chỉ được phép nhập số và dấu 1 dấu chấm.");
        }
        try{
            if(!StringUtils.isEmpty(fromCreatedDateStr)) this.fromCreatedDate = Utils.convertyyyyMMddToLocalDateTime(fromCreatedDateStr);
            if(!StringUtils.isEmpty(toCreatedDateStr)) this.toCreatedDate = Utils.convertyyyyMMddToLocalDateTime(toCreatedDateStr);
            if(!StringUtils.isEmpty(fromDeadlineStr)) this.fromDeadline = Utils.convertyyyyMMddToLocalDateTime(fromDeadlineStr);
            if(!StringUtils.isEmpty(toDeadlineStr)) this.toDeadline = Utils.convertyyyyMMddToLocalDateTime(toDeadlineStr);
        }catch (Exception e){
            throw new Exception("Không parse được ngày.");
        }
    }
}
