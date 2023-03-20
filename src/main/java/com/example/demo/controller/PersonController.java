package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.model.PersonDTO;
import com.example.demo.model.PersonFilterRequest;
import com.example.demo.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findList(@ModelAttribute("personFilterRequest") PersonFilterRequest personFilterRequest) {
        ModelAndView mv = new ModelAndView();
        personFilterRequest.stringToInstant();
        List<PersonEntity> resultLst = personService.findList(
                personFilterRequest.getName(), personFilterRequest.getAddress(),
                personFilterRequest.getPhone(), personFilterRequest.getEmail(),
                personFilterRequest.getUserId(), personFilterRequest.getFromId(),
                personFilterRequest.getToId(), personFilterRequest.getFromTotal(),
                personFilterRequest.getToTotal(), personFilterRequest.getFromCreatedDate(),
                personFilterRequest.getToCreatedDate(), personFilterRequest.getFromLastModifiedDate(),
                personFilterRequest.getToLastModifiedDate());

        List<PersonDTO> resultDtos = new ArrayList<>();

        for (PersonEntity person : resultLst){
            person.setCreatedDateStr(Utils.instantToString(person.getCreatedDate()));
            person.setLastModifiedDateStr(Utils.instantToString(person.getLastModifiedDate()));
            PersonDTO personDTO = new PersonDTO();
            BeanUtils.copyProperties(person,personDTO);
            resultDtos.add(personDTO);
        }

        mv.setViewName("/person/list");
        mv.addObject("resultDtos", resultDtos);
        return mv;
    }

    @RequestMapping(value = "/add-update", method = RequestMethod.POST)
    @ResponseBody
    public void createUpdate(@ModelAttribute PersonDTO personDTO) throws Exception{
        try{
            personService.createUpdate(personDTO);
        }catch (Exception ex){
            throw ex;
        }
    }
}