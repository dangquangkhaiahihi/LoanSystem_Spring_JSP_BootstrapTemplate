package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
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
    private String amount;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @Column(name = "IMAGE_URL")
    private String imgUrl;
}
