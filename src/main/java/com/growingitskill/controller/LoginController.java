package com.growingitskill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.growingitskill.service.BlogInfoService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private BlogInfoService blogInfoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String moveLogin(Model model) throws Exception {
		model.addAttribute(blogInfoService.findBlogInfo());
		
		return "login";
	}

}
