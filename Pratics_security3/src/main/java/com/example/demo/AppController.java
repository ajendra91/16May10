package com.example.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

@Controller
public class AppController {
	
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/mylogin")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request,Model model) {
		/*CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                .getName());
        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
            String token = csrf.getToken();
         System.out.println(token); 
         model.addAttribute("name", token);*/
		 String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		System.out.println(authorizationToken);
		return "admin";
	}

}
