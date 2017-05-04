package com.growingitskill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.growingitskill.domain.PostVO;
import com.growingitskill.service.PostService;

@Controller
@RequestMapping("/admin/post")
public class PostController {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listPage() throws Exception {
		return "admin/post/list";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public void newPage() throws Exception {
		
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String newPagePOST(PostVO vo, RedirectAttributes redirectAttributes) throws Exception {
		service.regist(vo);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/post";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void editPage() throws Exception {
	}

}
