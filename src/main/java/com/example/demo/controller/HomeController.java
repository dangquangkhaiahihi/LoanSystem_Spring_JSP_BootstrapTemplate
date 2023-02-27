package com.example.demo.controller;

import com.example.demo.common.Utils;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
