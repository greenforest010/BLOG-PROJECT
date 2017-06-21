package com.growingitskill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.PageMaker;
import com.growingitskill.service.PostService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Criteria criteria, Model model) throws Exception {
		model.addAttribute("list", postService.findList(criteria));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(postService.countCriteria(criteria));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "index";
	}
	
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "/post/{id}", method=RequestMethod.GET)
	public String read(@PathVariable("id") long id, Model model) throws Exception {
		model.addAttribute(postService.findById(id));
		
		return "details";
	}

}
