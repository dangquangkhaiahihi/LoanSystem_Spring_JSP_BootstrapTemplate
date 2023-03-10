package com.example.demo.controller;

import com.example.demo.model.RequestDto;
import com.example.demo.model.RequestFilterRequest;
import com.example.demo.service.RequestService;
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
@RequestMapping(value = "/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getRequestList(@ModelAttribute("requestFilterRequest") RequestFilterRequest requestFilterRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            requestFilterRequest.validateInput();
            List<RequestDto> requestDtos = requestService.filter(requestFilterRequest);
            mv.setViewName("/request/list");
            mv.addObject("requestDtos", requestDtos);
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView approve(@ModelAttribute("requestFilterRequest") RequestFilterRequest requestFilterRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            requestService.approveOrReject(requestFilterRequest.getRequestId(), true);
            mv.setView(new RedirectView("/request"));
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView reject(@ModelAttribute("requestFilterRequest") RequestFilterRequest requestFilterRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            requestService.approveOrReject(requestFilterRequest.getRequestId(), false);
            mv.setView(new RedirectView("/request"));
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
