package com.example.demo.service;

import com.example.demo.entity.TicketEntity;
import com.example.demo.service.dto.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface TicketService {
    void createUpdate(TicketDTO ticketDTO);

    Page<TicketEntity> findList(Boolean isPlus, String note, Long id, Long personId,
                                Long fromAmount, Long toAmount,
                                Instant fromCreatedDateInstant, Instant toCreatedDateInstant,
                                Instant fromDateOfTransInstant, Instant toDateOfTransInstant,
                                Pageable of);
}
