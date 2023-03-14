package com.example.demo.model;

import com.example.demo.common.Constant;
import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TraceDto {
    private Long id;
    private String loanerUserName;
    private Long debtorId;
    private Float remain;
    private Float interest;
    private Float amountThisMonth;
    private Float amountInterestThisMonth;
    private Float finalAmountThisMonth;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime prevDeadline;
    private LocalDateTime nextDeadline;
    private String remainStr;
    private String interestStr;
    private String amountThisMonthStr;
    private String amountInterestThisMonthStr;
    private String finalAmountThisMonthStr;
    private String createdAtStr;
    private String prevDeadlineStr;
    private String nextDeadlineStr;
    private LoanDto loan;

    public void setRemain(Float remain) {
        this.remain = remain;
        this.remainStr = StringUtils.convertFloatToString(remain);
    }

    public void setInterest(Float interest) {
        this.interest = interest;
        this.interestStr = Float.toString(interest) + "%";
    }
    public void setAmountThisMonth(Float amountThisMonth) {
        this.amountThisMonth = amountThisMonth;
        this.amountThisMonthStr = StringUtils.convertFloatToString(amountThisMonth);
    }
    public void setAmountInterestThisMonth(Float amountInterestThisMonth) {
        this.amountInterestThisMonth = amountInterestThisMonth;
        this.amountInterestThisMonthStr = StringUtils.convertFloatToString(amountInterestThisMonth);
    }
    public void setFinalAmountThisMonth(Float finalAmountThisMonth) {
        this.finalAmountThisMonth = finalAmountThisMonth;
        this.finalAmountThisMonthStr = StringUtils.convertFloatToString(finalAmountThisMonth);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        if (createdAt != null) {
            String stringValue = Utils.convertLocalDateTimeToddMMyyy(createdAt);
            this.createdAtStr = stringValue;
        } else {
            this.createdAtStr = "";
        }
    }

    public void setPrevDeadline(LocalDateTime prevDeadline) {
        this.prevDeadline = prevDeadline;
        if (prevDeadline != null) {
            String stringValue = Utils.convertLocalDateTimeToddMMyyy(prevDeadline);
            this.prevDeadlineStr = stringValue;
        } else {
            this.prevDeadlineStr = "";
        }
    }

    public void setNextDeadline(LocalDateTime nextDeadline) {
        this.nextDeadline = nextDeadline;
        if (nextDeadline != null) {
            String stringValue = Utils.convertLocalDateTimeToddMMyyy(nextDeadline);
            this.nextDeadlineStr = stringValue;
        } else {
            this.nextDeadlineStr = "";
        }
    }

    public void setStatus(String status) {
        if(Constant.TRACE_STATUS_NOT_YET.equals(status)){
            this.status = "Chưa trả kỳ này";
        }else if(Constant.TRACE_STATUS_PAID.equals(status)){
            this.status = "Đã trả kỳ này";
        }else if(Constant.TRACE_STATUS_OVERDUE.equals(status)){
            this.status = "Quá hạn";
        }else if(Constant.TRACE_STATUS_COMPLETE.equals(status)){
            this.status = "Đã hoàn thành ";
        }
    }
}