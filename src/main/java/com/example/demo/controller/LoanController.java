package com.example.demo.controller;

import com.example.demo.model.LoanDto;
import com.example.demo.model.LoanRequestAdd;
import com.example.demo.model.LoanRequestFilter;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/loan")
public class LoanController {
    @Autowired
    LoanService loanService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoanList(@ModelAttribute("loanRequest") LoanRequestFilter loanRequestFilter, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            loanRequestFilter.validateInput();
            List<LoanDto> loanDtos = loanService.filter(loanRequestFilter);
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addLoan(@ModelAttribute("loanRequestAdd") LoanRequestAdd loanRequestAdd, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            loanRequestAdd.validateInput();
            loanService.addLoan(loanRequestAdd);
            mv.setView(new RedirectView("/loan"));
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
}
