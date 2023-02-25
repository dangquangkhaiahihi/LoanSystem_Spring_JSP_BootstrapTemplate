package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "LOAN")
public class LoanEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT")
    private Float amount;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "DEADLINE")
    private LocalDateTime deadline;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "STATUS")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;

    //trong bảng ko có cột này
    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TraceUserLoanEntity> traces;
}
