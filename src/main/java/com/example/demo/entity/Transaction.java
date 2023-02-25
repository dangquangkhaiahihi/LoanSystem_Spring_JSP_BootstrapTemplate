//package com.example.demo.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@Table(name = "TRANSACTION")
//public class Transaction {
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "CREDITOR")
//    private Long creditor;
//
//    @Column(name = "DEBTOR")
//    private Long debtor;
//
//    @Column(name = "AMOUNT")
//    private Float amount;
//
//    @Column(name = "CREATED_AT")
//    private LocalDateTime createdAt;
//
//    @Column(name = "FINISHED_AT")
//    private LocalDateTime finishedAt;
//}
