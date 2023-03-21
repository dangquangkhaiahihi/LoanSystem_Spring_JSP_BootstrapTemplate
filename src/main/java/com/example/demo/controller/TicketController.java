package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.entity.TicketEntity;
import com.example.demo.model.PersonDTO;
import com.example.demo.model.TicketDTO;
import com.example.demo.model.TicketFilterRequest;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

        return result;
    }

    @RequestMapping(value = "/add-update", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public List<TicketEntity> createUpdate(@ModelAttribute TicketDTO ticketDTO) throws Exception{
        try{
            return ticketService.createUpdate(ticketDTO);
        }catch (Exception ex){
            throw ex;
        }
    }
}
