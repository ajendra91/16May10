package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String admin() {
		return "admin";
	}

}
