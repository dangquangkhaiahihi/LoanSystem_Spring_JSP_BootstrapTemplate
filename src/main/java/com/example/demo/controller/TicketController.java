package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.entity.TicketEntity;
import com.example.demo.model.TicketFilterRequest;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<TicketEntity> findList(@ModelAttribute("ticketFilterRequest") TicketFilterRequest ticketFilterRequest) {
        ticketFilterRequest.mapFERequestToObject();
        ticketFilterRequest.stringToInstant();
        List<TicketEntity> result = ticketService.findList(
                ticketFilterRequest.getIsPlus(), ticketFilterRequest.getNote(),
                ticketFilterRequest.getId(), ticketFilterRequest.getPersonId(),
                ticketFilterRequest.getFromAmount(), ticketFilterRequest.getToAmount(),
                ticketFilterRequest.getFromDateOfTrans(), ticketFilterRequest.getToDateOfTrans(),
                ticketFilterRequest.getFromLastModifiedDate(), ticketFilterRequest.getToLastModifiedDate());

        for (TicketEntity ticket : result){
            ticket.setDateOfTransStr(Utils.instantToString(ticket.getDateOfTrans()));
            ticket.setLastModifiedDateStr(Utils.instantToString(ticket.getLastModifiedDate()));
        }

        return result;
    }
}
