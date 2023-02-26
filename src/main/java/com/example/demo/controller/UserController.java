package com.example.demo.controller;

import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@ControllerAdvice
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/update-profile", method = RequestMethod.POST)
    public ModelAndView updateProfile(@ModelAttribute("userDto") UserDto userDto, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        try {
            UserDto userDto1 = userService.updateProfile(userDto);
            session.setAttribute("user-info", userDto1);
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        mv.setViewName("home");
        return mv;
    }
}
