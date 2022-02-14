package com.calc.exchgrate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	//root 경로 접속 controller
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		return "index";
	}
}
