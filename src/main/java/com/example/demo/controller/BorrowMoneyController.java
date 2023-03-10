package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.BorrowMoneyService;
import com.example.demo.service.LoanService;
import com.example.demo.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/borrow-money")
public class BorrowMoneyController {
    @Autowired
    LoanService loanService;

    @Autowired
    BorrowMoneyService borrowMoneyService;

    @Autowired
    TraceService traceService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getBorrowList(@ModelAttribute("loanRequest") LoanRequestFilter loanRequestFilter, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            loanRequestFilter.validateInput();
            List<LoanDto> loanDtos = loanService.filterForBorrow(loanRequestFilter);
            mv.setViewName("/borrow-money/list");
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

    @RequestMapping(value = "/request_borrow", method = RequestMethod.POST)
    public ModelAndView borrowMoney(@ModelAttribute("borrowMoneyRequest") BrowMoneyRequest borrowMoneyRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            borrowMoneyRequest.validateInput();
            borrowMoneyService.requestBorrowMoney(borrowMoneyRequest);
            mv.setView(new RedirectView("/borrow-money"));
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/delete-request", method = RequestMethod.DELETE)
    @ResponseBody
    public String lock(@ModelAttribute("loanId") Long loanId) {
        borrowMoneyService.deleteRequest(loanId);
        return "/borrow-money";
    }

    @RequestMapping(value = "/pay-debt", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView payDebt(@ModelAttribute("traceRequest") TraceRequest traceRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            traceService.payDebt(traceRequest);
            mv.setView(new RedirectView("/trace/list-debt"));
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }
}
