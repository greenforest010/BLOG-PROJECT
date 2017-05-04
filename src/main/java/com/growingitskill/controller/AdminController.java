package com.growingitskill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "admin/main";
	}
	
	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String category() {
		return "admin/category";
	}
	
	@RequestMapping(value = "tag", method = RequestMethod.GET)
	public String tag() {
		return "admin/tag";
	}
	
	@RequestMapping(value = "media", method = RequestMethod.GET)
	public String media() {
		return "admin/media";
	}

}
