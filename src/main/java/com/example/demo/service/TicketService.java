package com.example.demo.service;

import com.example.demo.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface TicketService {
    List<TicketEntity> findList(Boolean isPlus, String note, Long id, Long personId,
                                Long fromAmount, Long toAmount,
                                Instant fromDateOfTrans, Instant toDateOfTrans,
                                Instant fromLastModifiedDate, Instant toLastModifiedDate);
}
