package com.example.demo.controller;

import com.example.demo.entity.TicketEntity;
import com.example.demo.service.TicketService;
import com.example.demo.service.dto.ResponseDTO;
import com.example.demo.service.dto.TicketDTO;
import com.example.demo.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping(value = "")
    @ResponseBody
    public ResponseEntity<ResponseDTO> createUpdate(@Valid @RequestBody TicketDTO ticketDTO) {
        ticketService.createUpdate(ticketDTO);
        return ResponseEntity.ok().body(ResponseUtils.responseOK(null));
    }

    @GetMapping(value = "/find")
    @ResponseBody
    public ResponseEntity<ResponseDTO> findList(@RequestParam(value = "isPlus", required = false) Boolean isPlus,
                                                @RequestParam(value = "note", required = false) String note,
                                                @RequestParam(value = "id", required = false) Long id,
                                                @RequestParam(value = "fromAmount", required = false) Long fromAmount,
                                                @RequestParam(value = "toAmount", required = false) Long toAmount,
                                                @RequestParam(value = "personId") Long personId,
                                                @RequestParam(value = "fromDateOfTrans", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime fromDateOfTrans,
                                                @RequestParam(value = "toDateOfTrans", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime toDateOfTrans,
                                                @RequestParam(value = "fromCreatedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime fromCreatedDate,
                                                @RequestParam(value = "toCreatedDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") ZonedDateTime toCreatedDate,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Instant fromCreatedDateInstant = Objects.nonNull(fromCreatedDate) ? fromCreatedDate.toInstant() : null;
        Instant toCreatedDateInstant = Objects.nonNull(toCreatedDate) ? toCreatedDate.toInstant() : null;
        Instant fromDateOfTransInstant = Objects.nonNull(fromDateOfTrans) ? fromDateOfTrans.toInstant() : null;
        Instant toDateOfTransInstant = Objects.nonNull(toDateOfTrans) ? toDateOfTrans.toInstant() : null;
        Page<TicketEntity> pageResult = ticketService.findList(
                isPlus, note, id, personId,
                fromAmount, toAmount,
                fromCreatedDateInstant, toCreatedDateInstant,
                fromDateOfTransInstant, toDateOfTransInstant,
                PageRequest.of(page, size));
        ResponseDTO response = ResponseUtils.responseOK(pageResult.toList());
        response.getMeta().setTotal(pageResult.getTotalElements());
        response.getMeta().setPage(pageResult.getNumber());
        response.getMeta().setSize(pageResult.getSize());

        return ResponseEntity.ok().body(response);
    }
}
