package com.growingitskill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.growingitskill.service.PostService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		model.addAttribute("list", postService.listAll());
		
		return "index";
	}
	
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "details", method = RequestMethod.GET)
	public String details() {
		return "details";
	}

}
