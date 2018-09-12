package com.notinha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SisCppController {
	
	@RequestMapping
	public String carregaDashboard(){
		
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/entrar")
    public String entrar() {
        return "entrar";
    }
	
	

}
