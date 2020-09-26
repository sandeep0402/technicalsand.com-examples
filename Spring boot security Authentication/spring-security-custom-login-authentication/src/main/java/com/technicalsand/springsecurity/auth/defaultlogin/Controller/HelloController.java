package com.technicalsand.springsecurity.auth.defaultlogin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("home");
	}

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
}
