package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.model.TicketDTO;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

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

        for (TicketEntity ticket : result) {
            ticket.setDateOfTransStr(Utils.instantToString(ticket.getDateOfTrans()));
            ticket.setLastModifiedDateStr(Utils.instantToString(ticket.getLastModifiedDate()));
        }
        return result;
    }

    @Override
    @Transactional
    public List<TicketEntity> createUpdate(TicketDTO ticketDTO) throws Exception {
        try {
            ticketDTO.validateRequest();
            ticketRepository.save(ticketDTO.getAmount(), ticketDTO.getIsPlus(),
                    ticketDTO.getNote(), null, ticketDTO.getPersonId(),
                    ticketDTO.getDateOfTrans(), Instant.now());
            PersonEntity person = personRepository.findByPersonId(ticketDTO.getPersonId());
            UserEntity user = userRepository.findById(person.getUser().getId()).get();
            Long amount = user.getAmount();

            Long totalAmount = person.getTotalAmount();
            if (ticketDTO.getIsPlus()) {
                totalAmount += ticketDTO.getAmount();
                amount += ticketDTO.getAmount();
            } else {
                totalAmount -= ticketDTO.getAmount();
                amount -= ticketDTO.getAmount();
            }
            personRepository.updatePersonTotalAmount(person.getId(), totalAmount, Instant.now());
            userRepository.updateAmount(user.getId(), amount);
            return findList(null, null, null, ticketDTO.getPersonId(), null, null, null, null, null, null);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
