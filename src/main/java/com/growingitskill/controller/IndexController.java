package com.growingitskill.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.PageMaker;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.domain.TagVO;
import com.growingitskill.exception.CategoryNotFoundException;
import com.growingitskill.exception.PostNotFoundException;
import com.growingitskill.exception.TagNotFoundException;
import com.growingitskill.service.AboutService;
import com.growingitskill.service.BlogInfoService;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.PostService;
import com.growingitskill.service.TagService;

@Controller
@RequestMapping("/")
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TagService tagService;

	@Autowired
	private AboutService aboutService;
	
	@Autowired
	private BlogInfoService blogInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public String moveIndex(SearchCriteria searchCriteria, Model model) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		List<PostVO> list = postService.findPostsWithCriteria(searchCriteria);

		int countPostWithCriteria = postService.countPostWithCriteria(searchCriteria);

		return makeIndex(list, searchCriteria, countPostWithCriteria, model);
	}

	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String moveAbout(Model model) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		model.addAttribute("content", aboutService.findAbout());

		return "about";
	}

	@RequestMapping(value = "/post/{slugTitle}", method = RequestMethod.GET)
	public String movePostDetailsBySlugTitle(@PathVariable("slugTitle") String slugTitle, Model model)
			throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		PostVO postVO = postService.findPostBySlugTitle(slugTitle);

		if (postVO == null) {
			throw new PostNotFoundException();
		}

		model.addAttribute(postVO);
		model.addAttribute("tagList", tagService.findTagByPostId(postVO.getId()));

		return "details";
	}

	@RequestMapping(value = "/category/{slugTerm}", method = RequestMethod.GET)
	public String moveIndexByCategory(@PathVariable("slugTerm") String slugTerm, SearchCriteria searchCriteria,
			Model model) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		CategoryVO categoryVO = categoryService.findCategoryBySlugTerm(slugTerm);

		if (categoryVO == null) {
			throw new CategoryNotFoundException();
		}

		Set<Long> categoryLevelSet = makeCategoryLevelSet(slugTerm);

		List<PostVO> list = postService.findPostsWithCriteriaByCategory(categoryLevelSet, searchCriteria);

		int countPost = postService.countPostByCategory(categoryLevelSet);

		return makeIndex(list, searchCriteria, countPost, model);
	}

	@RequestMapping(value = "/tag", method = RequestMethod.GET)
	public String moveTag(Model model) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		model.addAttribute("tags", tagService.findTags());

		model.addAttribute("postCountByCategory", getPostCountByCategory());

		return "tags";
	}

	@RequestMapping(value = "/tag/{slugTerm}", method = RequestMethod.GET)
	public String moveIndexByTagSlugTerm(@PathVariable("slugTerm") String slugTerm, SearchCriteria searchCriteria,
			Model model) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		TagVO tagVO = tagService.findTagBySlugTerm(slugTerm);

		if (tagVO == null) {
			throw new TagNotFoundException();
		}

		List<PostVO> list = postService.findPostsWithCriteriaByTag(slugTerm, searchCriteria);

		int countPostWithCriteria = postService.countPostWithCriteriaByTag(slugTerm, searchCriteria);

		return makeIndex(list, searchCriteria, countPostWithCriteria, model);
	}

	private String makeIndex(List<PostVO> list, SearchCriteria searchCriteria, int countPost, Model model)
			throws Exception {
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(countPost);

		model.addAttribute("pageMaker", pageMaker);

		model.addAttribute("postCountByCategory", getPostCountByCategory());

		return "index";
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

	private String getPostCountByCategory() throws Exception {
		List<CategoryVO> categories = categoryService.findCategories();

		Map<Long, Integer> map = new HashMap<>();

		for (CategoryVO categoryVO : categories) {
			Set<Long> categoryLevelSet = makeCategoryLevelSet(categoryVO.getSlugTerm());

			int countCategoryCriteria = postService.countPostByCategory(categoryLevelSet);

			map.put(categoryVO.getId(), countCategoryCriteria);
		}

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.writeValueAsString(map);
	}

}
