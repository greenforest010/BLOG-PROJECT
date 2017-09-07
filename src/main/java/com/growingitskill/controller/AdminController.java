package com.growingitskill.controller;

import java.security.Principal;

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
import com.growingitskill.util.MemberUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AboutService aboutService;

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private MemberUtils memberUtils;

	@RequestMapping(method = RequestMethod.GET)
	public String moveIndex(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());

		return "admin/main";
	}

	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String moveCategory(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		return "admin/category";
	}

	@RequestMapping(value = "tag", method = RequestMethod.GET)
	public String moveTag(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		return "admin/tag";
	}

	@RequestMapping(value = "media", method = RequestMethod.GET)
	public String moveMedia(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		model.addAttribute("list", attachmentService.listAll());

		return "admin/media";
	}

	@RequestMapping(value = "about-edit", method = RequestMethod.GET)
	public String moveAboutEdit(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		model.addAttribute("content", aboutService.findAbout());

		return "admin/about-edit";
	}

	@RequestMapping(value = "about-edit", method = RequestMethod.PUT)
	public String modifyContentInAboutEdit(String content, RedirectAttributes redirectAttributes) throws Exception {
		aboutService.modifyAboutContent(content);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/about-edit";
	}

}
