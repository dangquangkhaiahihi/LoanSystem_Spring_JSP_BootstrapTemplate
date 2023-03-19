package com.example.demo.security;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.exception.CaptchaException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
public class CustomBeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public CustomBeforeAuthenticationFilter() {
        setUsernameParameter("username");
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        request.getSession().setAttribute("error-message", null);
        //Check capcha
        String captcha = request.getParameter("captcha");

        System.out.println("captcha input : " + captcha);
        System.out.println("captcha session : " + request.getSession().getAttribute("captcha"));

        if(captcha != null && !captcha.equals(request.getSession().getAttribute("captcha"))) {
            throw new CaptchaException("Captcha mismatch");
        }
        return super.attemptAuthentication(request, response);
    }
}