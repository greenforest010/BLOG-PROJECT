package com.growingitskill.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.MemberVO;
import com.growingitskill.domain.PostVO;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.MemberService;
import com.growingitskill.service.PostService;

@Controller
@RequestMapping("/admin/post")
public class PostController {

	public static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	public String listPage(Model model) throws Exception {	
		List<PostVO> list = postService.listAll();
		
		for (PostVO postVO : list) {
			logger.info("post -> " + postVO.toString());
		}
		
		model.addAttribute("list", postService.listAll());

		return "admin/post/list";
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public void newPage(Model model) throws Exception {
		model.addAttribute("categoryList", categoryService.listAll());
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String newPagePOST(PostVO postVO, MemberVO memberVO, @RequestParam("categoryId") long categoryId,
			RedirectAttributes redirectAttributes) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setCategoryVO(categoryVO);

		postVO.setAuthor(memberService.getById(memberVO));
		
		logger.info(postVO.toString());

		postService.regist(postVO);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void editPage(long id, Model model) throws Exception {
		logger.info("editPage get ......");

		model.addAttribute(postService.read(id));
		model.addAttribute("categoryList", categoryService.listAll());
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editPagePOST(PostVO postVO, @RequestParam("categoryId") long categoryId, RedirectAttributes redirectAttributes) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);
		
		postVO.setCategoryVO(categoryVO);

		postService.modify(postVO);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String remove(@RequestParam("postId") long[] postId, RedirectAttributes redirectAttributes)
			throws Exception {
		postService.remove(postId);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

}
