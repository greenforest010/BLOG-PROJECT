package com.growingitskill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.growingitskill.domain.PostVO;
import com.growingitskill.service.PostService;

@Controller
@RequestMapping("/admin/post")
public class PostController {
	
	public static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listPage(Model model) throws Exception {
		model.addAttribute("list", service.listAll());
		
		return "admin/post/list";
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public void newPage() throws Exception {
		logger.info("newPage get ......");
	}
	
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String newPagePOST(PostVO vo, RedirectAttributes redirectAttributes) throws Exception {
		service.regist(vo);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/post";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void editPage(long id, Model model) throws Exception {
		logger.info("editPage get ......");
		
		model.addAttribute(service.read(id));
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editPagePOST(PostVO vo, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("edit post ......");
		
		service.modify(vo);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/post";
	}
	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(@RequestParam("postId") long[] postId, RedirectAttributes redirectAttributes) throws Exception {
		service.remove(postId);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/post";
	}

}
