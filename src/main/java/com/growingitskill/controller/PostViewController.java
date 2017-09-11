package com.growingitskill.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.PageMaker;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.exception.CategoryNotFoundException;
import com.growingitskill.exception.PostNotFoundException;
import com.growingitskill.mapper.CategoryRelationMapper;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.PostService;
import com.growingitskill.service.TagService;
import com.growingitskill.util.MemberUtils;
import com.growingitskill.util.TagUtils;

@Controller
@RequestMapping("/admin/post")
public class PostViewController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PostViewController.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRelationMapper categoryRelationMapper;

	@Autowired
	private PostService postService;

	@Autowired
	private TagService tagService;
	
	@Autowired
	private MemberUtils memberUtils;

	@Autowired
	private TagUtils tagUtils;

	@RequestMapping(method = RequestMethod.GET)
	public String movePost(@ModelAttribute("criteria") SearchCriteria searchCriteria, Model model, Principal principal)
			throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		int requestPerPageNum = getRequestPerPageNum(searchCriteria);

		searchCriteria.setPerPageNum(requestPerPageNum);

		model.addAttribute("categoryList", categoryService.findCategories());

		List<PostVO> list = postService.findPostsWithCriteria(searchCriteria);

		int countCriteria = postService.countPostWithCriteria(searchCriteria);

		return makePostList(list, searchCriteria, countCriteria, model);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public PostVO setCategoryInPost(@RequestBody Map<String, Long> map) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(map.get("categoryId"));

		PostVO postVO = new PostVO();
		postVO.setId(map.get("id"));
		postVO.setCategoryVO(categoryVO);

		categoryRelationMapper.createCategoryRelation(postVO);

		PostVO result = postService.findPostById(map.get("id"));

		return result;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String moveEdit(@PathVariable("id") long id, @ModelAttribute Criteria criteria, Model model, Principal principal)
			throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		PostVO postVO = postService.findPostById(id);

		if (postVO == null) {
			throw new PostNotFoundException();
		}

		model.addAttribute(postService.findPostById(id));
		model.addAttribute("categoryList", categoryService.findLeafCategories());

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTagList = objectMapper.writeValueAsString(tagService.findTagTermByPostId(id));

		model.addAttribute("tagList", jsonTagList);

		return "admin/post/edit";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String modifyPost(PostVO postVO, @RequestParam("categoryId") long categoryId,
			@RequestParam("tags") String tags, Criteria criteria, RedirectAttributes redirectAttributes)
			throws Exception {
		tagUtils.connectPostAndModifiedTagList(tags, postVO);

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setCategoryVO(categoryVO);

		postService.modifyPost(postVO);

		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

	@RequestMapping(value = "category/{slugTerm}", method = RequestMethod.GET)
	public String movePostByCategory(@PathVariable("slugTerm") String slugTerm, SearchCriteria searchCriteria,
			Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		CategoryVO categoryVO = categoryService.findCategoryBySlugTerm(slugTerm);

		if (categoryVO == null) {
			throw new CategoryNotFoundException();
		}

		int requestPerPageNum = getRequestPerPageNum(searchCriteria);

		searchCriteria.setPerPageNum(requestPerPageNum);

		model.addAttribute("categoryList", categoryService.findCategories());

		Set<Long> categoryLevelSet = makeCategoryLevelSet(slugTerm);

		List<PostVO> list = postService.findPostsWithCriteriaByCategory(categoryLevelSet, searchCriteria);

		int countCriteria = postService.countPostByCategory(categoryLevelSet);

		return makePostList(list, searchCriteria, countCriteria, model);
	}

	private String makePostList(List<PostVO> list, SearchCriteria searchCriteria, int countCriteria, Model model) {
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setDisplayPageNum(10);
		pageMaker.setTotalCount(countCriteria);

		model.addAttribute("pageMaker", pageMaker);

		return "admin/post/list";
	}

	private Set<Long> makeCategoryLevelSet(String slugTerm) throws Exception {
		List<CategoryLevel> listCategoryLevel = categoryService.findCategoryLevel(slugTerm);

		Set<Long> set = new HashSet<>();

		for (CategoryLevel categoryLevel : listCategoryLevel) {
			set.add(categoryLevel.getLevel1());
			set.add(categoryLevel.getLevel2());
			set.add(categoryLevel.getLevel3());
			set.add(categoryLevel.getLevel4());
		}

		if (set.contains((long) 0)) {
			set.remove((long) 0);
		}

		return set;
	}

	private int getRequestPerPageNum(SearchCriteria searchCriteria) {
		int result = (searchCriteria.getPerPageNum() < 20) ? 20 : searchCriteria.getPerPageNum();

		return result;
	}

}
