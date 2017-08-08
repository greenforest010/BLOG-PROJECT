package com.growingitskill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.growingitskill.domain.PageMaker;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.service.PostService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(SearchCriteria searchCriteria, Model model) throws Exception {
		model.addAttribute("list", postService.findList(searchCriteria));
		
		LOGGER.info("list is " + postService.findList(searchCriteria).isEmpty());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(postService.countCriteria(searchCriteria));
		
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
