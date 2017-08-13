package com.growingitskill.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.growingitskill.domain.MemberVO;
import com.growingitskill.service.MemberService;

@Controller
@RequestMapping("/admin/profile")
public class ProfileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private MemberService memberService;

	@RequestMapping(method = RequestMethod.GET)
	public String profilePage(Model model, Principal principal) throws Exception {
		String loginId = principal.getName();

		LOGGER.info("loginId: " + loginId);

		model.addAttribute("member", memberService.findMemberByLoginId(loginId));

		return "admin/profile";
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid MemberVO memberVO, Errors errors, RedirectAttributes redirectAttributes) throws Exception {
		if (errors.hasErrors()) {
			redirectAttributes.addFlashAttribute("msg", "fail");
			
			return "redirect:/admin/profile";
		}
		
		LOGGER.info(memberVO.toString());
		
		memberService.modifyMemberByLoginId(memberVO);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/profile";
	}

}
