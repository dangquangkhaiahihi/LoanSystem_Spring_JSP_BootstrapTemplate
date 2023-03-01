package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.model.LoanDto;
import com.example.demo.model.LoanRequest;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping(value = "/loan")
public class LoanController {
    @Autowired
    LoanService loanService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoanList(@ModelAttribute("loanRequest") LoanRequest loanRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            loanRequest.validateInput();
            List<LoanDto> loanDtos = loanService.filter(loanRequest);
            mv.setViewName("/loan/list");
            mv.addObject("loanDtos", loanDtos);
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    @ResponseBody
    public String lock(@ModelAttribute("loanId") Long loanId) {
        loanService.changeStatus(loanId,false);
        return "/loan";
    }

    @RequestMapping(value = "/unlock", method = RequestMethod.POST)
    @ResponseBody
    public String unlock(@ModelAttribute("loanId") Long loanId) {
        loanService.changeStatus(loanId,true);
        return "/loan";
    }

    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public void ping(@ModelAttribute("message") String message) {
        System.out.println("pong");
        System.out.println("message : "+message);
    }
}
