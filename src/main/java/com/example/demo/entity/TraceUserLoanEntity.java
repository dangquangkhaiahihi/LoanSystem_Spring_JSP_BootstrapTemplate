package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TRACE_USER_LOAN")
public class TraceUserLoanEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DEBTOR_ID")
    private Long debtorId;

    @Column(name = "REMAIN")
    private Float remain;

    @Column(name = "INTERST")
    private Float interest;

    @Column(name = "FINAL_AMOUNT")
    private Float finalAmount;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "LOAN_ID", nullable = false)
    private LoanEntity loan;
}
