package com.growingitskill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.growingitskill.service.AboutService;
import com.growingitskill.service.AttachmentService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private AboutService aboutService;

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
	public String media(Model model) throws Exception {
		model.addAttribute("list", attachmentService.listAll());

		return "admin/media";
	}

	@RequestMapping(value = "about-edit", method = RequestMethod.GET)
	public String aboutEdit(Model model) throws Exception {
		model.addAttribute("content", aboutService.find());

		return "admin/about-edit";
	}

	@RequestMapping(value = "about-edit", method = RequestMethod.PUT)
	public String aboutEditPUT(String content, RedirectAttributes redirectAttributes) throws Exception {
		aboutService.modify(content);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/about-edit";
	}

}
