package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.entity.PersonEntity;
import com.example.demo.model.PersonDTO;
import com.example.demo.model.PersonFilterRequest;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

        for (PersonEntity person : resultLst){
            person.setCreatedDateStr(Utils.instantToString(person.getCreatedDate()));
            person.setLastModifiedDateStr(Utils.instantToString(person.getLastModifiedDate()));
        }

        mv.setViewName("/person/list");
        mv.addObject("resultLst", resultLst);
        return mv;
    }

    @RequestMapping(value = "/add-update", method = RequestMethod.POST)
    @ResponseBody
    public void createUpdate(@ModelAttribute PersonDTO personDTO) throws Exception{
        try{
            personDTO.validateRequest();
            personService.createUpdate(personDTO);
        }catch (Exception ex){
            throw ex;
        }
    }
}