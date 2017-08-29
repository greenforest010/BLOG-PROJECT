package com.growingitskill.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.growingitskill.domain.TagVO;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.MemberService;
import com.growingitskill.service.PostService;
import com.growingitskill.service.TagService;

@Controller
@RequestMapping("/admin/post/new")
public class PostNewController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PostNewController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void newPage(@ModelAttribute Criteria criteria, Model model) throws Exception {
		model.addAttribute("categoryList", categoryService.listLeafCategory());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String newPagePOST(PostVO postVO, @RequestParam("loginId") String loginId,
			@RequestParam("categoryId") long categoryId, @RequestParam("tags") String tags, RedirectAttributes redirectAttributes) throws Exception {
		LOGGER.info("tags[" + tags + "]");

		if (tags != null && !tags.isEmpty()) {
			String[] tagTerms = tags.split(",");

			// 새로운 태그들 생성
			List<String> existentTagTermList = tagService.findTagTermByTerms(tagTerms);

			List<String> newTermList = new ArrayList<>();
			Collections.addAll(newTermList, tagTerms);

			for (String term : existentTagTermList) {
				newTermList.remove(term);
			}

			List<TagVO> newTagList = new ArrayList<>();

			for (String term : newTermList) {
				TagVO tagVO = new TagVO();
				tagVO.setTerm(term);
				tagVO.setSlugTerm(term);

				newTagList.add(tagVO);
			}

			if (newTagList.size() > 0) {
				tagService.addTags(newTagList);
			}

			// 글-태그 연결
			List<TagVO> tagList = new ArrayList<>();
			
			List<Long> tagIdList = tagService.findTagIdByTerms(tagTerms);

			for (Long id : tagIdList) {
				TagVO tagVO = new TagVO();
				tagVO.setId(id);

				tagList.add(tagVO);
			}

			postVO.setTagList(tagList);
		}

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setCategoryVO(categoryVO);

		postVO.setAuthor(memberService.findIdByLoginId(loginId));

		LOGGER.info(postVO.toString());

		postService.regist(postVO);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

}
