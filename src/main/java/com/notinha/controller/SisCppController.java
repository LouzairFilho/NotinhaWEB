package com.notinha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SisCppController {
	
	@RequestMapping
	public String carregaDashboard(){
		
		return "index";
	}

}
