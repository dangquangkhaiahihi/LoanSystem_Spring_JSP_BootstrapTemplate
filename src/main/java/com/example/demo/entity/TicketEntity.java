package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "TICKET")
public class TicketEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "IS_PLUS")
    private Long isPlus;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "DATE_OF_TRANS")
    private LocalDateTime dateOfTrans;

    @Column(name = "IMAGE_URL")
    private String imgUrl;

    @ManyToOne
    @JsonIgnoreProperties(value = "", allowGetters = true)
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;
}
