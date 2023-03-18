package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	@Autowired
	private HttpSession session;

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		UserDto userDto = userService.getUserDetailByUsername(Utils.getCurrentUser().getName());
		session.setAttribute("user-info",userDto);
		return mv;
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public ModelAndView logOutPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = Utils.getCurrentUser();
		if (auth != null) {
			SecurityContextHolder.clearContext();
			new SecurityContextLogoutHandler().logout(request, response, auth);
			request.getSession(false).invalidate();
		}
		mv.setViewName("login");
		return mv;
	}


	@RequestMapping("/register-page")
	public String registerPage() {
		return "register";
	}

	@RequestMapping("/error-not-logged-in")
	public String errorNotLoggedIn() {
		return "error-not-logged-in";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView addLoan(@ModelAttribute("userDto") UserDto userDto, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			userDto.validateInput();
			userService.register(userDto);
			mv.setView(new RedirectView("/login"));
		} catch (Exception ex) {
			String goBackUrl = request.getHeader("referer");
			mv.addObject("errorMessage", ex.getMessage());
			mv.addObject("/login", goBackUrl);
			mv.setViewName("error-not-logged-in");
			return mv;
		}
		return mv;
	}
}
