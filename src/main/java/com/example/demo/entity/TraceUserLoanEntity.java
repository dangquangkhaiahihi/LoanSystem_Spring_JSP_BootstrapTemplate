package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TRACE_USER_LOAN")
public class TraceUserLoanEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOANER_USER_NAME")
    private String loanerUserName;

    @Column(name = "DEBTOR_USER_NAME")
    private String debtorUserName;

    @Column(name = "REMAIN")
    private Float remain;

    @Column(name = "INTERST")
    private Float interest;

    @Column(name = "AMOUNT_PER_MONTH")
    private Float amountPerMonth;

    @Column(name = "AMOUNT_THIS_MONTH")
    private Float amountThisMonth;

    @Column(name = "AMOUNT_INTEREST_THIS_MONTH")
    private Float amountInterestThisMonth;

    @Column(name = "AMOUNT_NEXT_MONTH")
    private Float amountNextMonth;

    @Column(name = "AMOUNT_INTEREST_NEXT_MONTH")
    private Float amountInterestNextMonth;

    @Column(name = "FINAL_AMOUNT_THIS_MONTH")
    private Float finalAmountThisMonth;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "PREV_DEADLINE")
    private LocalDateTime prevDeadline;

    @Column(name = "NEXT_DEADLINE")
    private LocalDateTime nextDeadline;

    @ManyToOne
    @JoinColumn(name = "LOAN_ID", nullable = false)
    private LoanEntity loan;
}
