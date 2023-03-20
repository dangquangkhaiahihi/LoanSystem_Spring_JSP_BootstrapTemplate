package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.entity.TicketEntity;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<TicketEntity> findList(Boolean isPlus, String note, Long id, Long personId,
                                       Long fromAmount, Long toAmount,
                                       Instant fromDateOfTrans, Instant toDateOfTrans,
                                       Instant fromLastModifiedDate, Instant toLastModifiedDate) {
        note = StringUtils.isEmpty(note) ? null : "%" + note.toUpperCase() + "%";
        List<TicketEntity> result = ticketRepository.findList(
                isPlus, note, id, personId,
                fromAmount, toAmount,
                fromDateOfTrans, toDateOfTrans,
                fromLastModifiedDate, toLastModifiedDate);
        return result;
    }
}
