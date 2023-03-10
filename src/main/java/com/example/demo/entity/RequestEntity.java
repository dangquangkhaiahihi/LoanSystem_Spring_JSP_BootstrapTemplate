package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class RequestEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LOAN_ID", nullable = false)
    private LoanEntity loan;

    @ManyToOne
    @JoinColumn(name = "DEBTOR_ID", nullable = false)
    private UserEntity debtor;

    @ManyToOne
    @JoinColumn(name = "LOANER_ID", nullable = false)
    private UserEntity loaner;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

}
