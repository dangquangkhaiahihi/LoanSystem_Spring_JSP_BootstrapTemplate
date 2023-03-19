package com.example.demo.controller;

import com.example.demo.common.Constant;
import com.example.demo.model.ChangePassDto;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/update-profile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String updateProfile(@ModelAttribute  UserDto userDto, HttpServletRequest request) throws Exception{
        try {
            UserDto userDto1 = userService.updateProfile(userDto);
            session.setAttribute("user-info", userDto1);
        } catch (Exception ex) {
            session.setAttribute("error-message", ex.getMessage());
            throw ex;
        }

        String goBackUrl = request.getHeader("referer");
        for(String url : Constant.cantRedirectUrls){
            if(goBackUrl.contains(url)){
                goBackUrl="/home";
                break;
            }
        }
        return goBackUrl;
    }

    @RequestMapping(value = "/change-pass", method = RequestMethod.POST)
    public ModelAndView changePass(@ModelAttribute("changePassDto") ChangePassDto changePassDto, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        try {
            userService.changePass(changePassDto);
        } catch (Exception ex) {
            String goBackUrl = request.getHeader("referer");
            for(String url : Constant.cantRedirectUrls){
                if(goBackUrl.contains(url)){
                    goBackUrl="/home";
                    break;
                }
            }
            mv.addObject("errorMessage", ex.getMessage());
            mv.addObject("goBackUrl", goBackUrl);
            mv.setViewName("error");
            return mv;
        }
        mv.setView(new RedirectView("/logout"));
        return mv;
    }
}
