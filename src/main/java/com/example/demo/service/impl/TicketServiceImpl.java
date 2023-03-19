package com.example.demo.service.impl;

import com.example.demo.common.StringUtils;
import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.entity.TicketEntity;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.TicketDTO;
import com.example.demo.service.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final PersonRepository personRepository;

    private final TicketMapper ticketMapper;

    private final UserService userService;

    @Override
    @Transactional
    public void createUpdate(TicketDTO ticketDTO) {
        enrichTicketInfo(ticketDTO);
        Optional<PersonEntity> personOpt = personRepository.findById(ticketDTO.getPerson().getId());
        PersonEntity personEntity = personOpt.get();
        updateAmountOfUser(ticketDTO, personEntity);

        TicketEntity ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setPerson(personEntity);
        if (Objects.isNull(ticket.getId())) {
            ticketRepository.save(ticket.getAmount(), ticket.getIsPlus(),
                    ticket.getNote(), ticket.getDateOfTrans(),
                    ticket.getImgUrl(), ticket.getPerson().getId(),
                    ticket.getCreatedBy(), ticket.getCreatedDate(),
                    ticket.getLastModifiedBy(), Instant.now());
        } else {
            ticketRepository.updateImg(ticket.getImgUrl(), ticket.getId());
        }

    }

    @Override
    public Page<TicketEntity> findList(Boolean isPlus, String note, Long id, Long personId,
                                       Long fromAmount, Long toAmount,
                                       Instant fromCreatedDateInstant, Instant toCreatedDateInstant,
                                       Instant fromDateOfTransInstant, Instant toDateOfTransInstant,
                                       Pageable pageable) {
        note = StringUtils.isEmpty(note) ? null : "%" + note.toUpperCase() + "%";
        Page<TicketEntity> page = ticketRepository.findList(
                isPlus, note, id, personId,
                fromAmount, toAmount,
                fromCreatedDateInstant, toCreatedDateInstant,
                fromDateOfTransInstant, toDateOfTransInstant,
                pageable);
        return page;
    }

    private void updateAmountOfUser(TicketDTO ticketDTO, PersonEntity personEntity) {
        if (Objects.nonNull(ticketDTO.getPerson().getId())) {
            Optional<TicketEntity> ticketOpt = ticketRepository.findById(ticketDTO.getId());
            if (ticketOpt.isPresent()) {
                TicketEntity ticket = ticketOpt.get();
                if (!ticket.getAmount().equals(ticketDTO.getAmount())
                        || !ticket.getIsPlus().equals(ticketDTO.getIsPlus())) {
                    userService.updateAmount(personEntity, ticket.getAmount(), ticket.getIsPlus());
                    userService.updateAmount(personEntity, ticketDTO.getAmount(), ticketDTO.getIsPlus());
                }
            } else {
                userService.updateAmount(personEntity, ticketDTO.getAmount(), ticketDTO.getIsPlus());
            }
        }
    }

    private void enrichTicketInfo(TicketDTO ticketDTO) {
        String currentUser = Utils.auditorAware().getCurrentAuditor().orElse("SYSTEM");
        ticketDTO.setLastModifiedBy(currentUser);
        ticketDTO.setCreatedBy(StringUtils.isEmpty(ticketDTO.getCreatedBy()) ? currentUser : ticketDTO.getCreatedBy());
        ticketDTO.setCreatedDate(Objects.isNull(ticketDTO.getCreatedDate()) ? Instant.now() : ticketDTO.getCreatedDate());
    }
}
