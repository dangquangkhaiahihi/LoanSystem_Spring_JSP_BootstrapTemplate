package com.example.demo.controller;

import com.example.demo.model.RequestFilterRequest;
import com.example.demo.model.TraceDto;
import com.example.demo.model.TraceRequest;
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
@RequestMapping(value = "/trace")
public class TraceController {
    @Autowired
    TraceService traceService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTraceList(@ModelAttribute("traceFilterRequest") RequestFilterRequest traceFilterRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            traceFilterRequest.validateInput();
            List<TraceDto> traces = traceService.filter(traceFilterRequest);
            mv.setViewName("/trace/list");
            mv.addObject("traces", traces);
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/list-debt",method = RequestMethod.GET)
    public ModelAndView getTraceListDebt(@ModelAttribute("traceFilterRequest") RequestFilterRequest traceFilterRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            traceFilterRequest.validateInput();
            List<TraceDto> traces = traceService.filterDebt(traceFilterRequest);
            mv.setViewName("/trace/list-debt");
            mv.addObject("traces", traces);
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        return mv;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView update(@ModelAttribute("traceRequest") TraceRequest traceRequest, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            traceService.updateTrace(traceRequest);
            mv.setView(new RedirectView("/trace"));
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
