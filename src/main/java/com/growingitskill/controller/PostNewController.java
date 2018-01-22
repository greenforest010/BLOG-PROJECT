package com.growingitskill.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.PostVO;
import com.growingitskill.service.BlogInfoService;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.PostService;
import com.growingitskill.util.MemberUtils;
import com.growingitskill.util.SlugUtils;
import com.growingitskill.util.TagUtils;
import com.growingitskill.util.TranslationUtils;

@Controller
@RequestMapping("/admin/post/new")
public class PostNewController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PostNewController.class);
	
	@Autowired
	private BlogInfoService blogInfoService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;
	
	@Autowired 
	private MemberUtils memberUtils;

	@Autowired
	private SlugUtils slugUtils;

	@Autowired
	private TagUtils tagUtils;

	@Autowired
	private TranslationUtils translationUtils;

	@RequestMapping(method = RequestMethod.GET)
	public void movePostNew(@ModelAttribute Criteria criteria, Model model, Principal principal) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		memberUtils.makeMemberModel(model, principal.getName());
		
		model.addAttribute("categoryList", categoryService.findLeafCategories());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerPostInPostNew(PostVO postVO, Principal principal, @RequestParam("categoryId") long categoryId,
			@RequestParam("tags") String tags, RedirectAttributes redirectAttributes )
			throws Exception {
		tagUtils.connnectPostAndNewTagList(tags, postVO);

		String slugTitle = slugUtils.slug(translationUtils.translate(postVO.getTitle(), "ko", "en"));

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setAuthor(memberUtils.getMemberIdByLoginId(principal.getName()));
		postVO.setCategoryVO(categoryVO);
		postVO.setSlugTitle(slugTitle);

		postService.registPost(postVO);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

}
