package com.growingitskill.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.PageMaker;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
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

	@RequestMapping(method = RequestMethod.GET)
	public String index(SearchCriteria searchCriteria, Model model) throws Exception {
		List<PostVO> list = postService.findList(searchCriteria);

		int countCriteria = postService.countCriteria(searchCriteria);

		return makeIndex(list, searchCriteria, countCriteria, model);
	}

	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	public String read(@PathVariable("id") long id, Model model) throws Exception {
		
		model.addAttribute(postService.findById(id));
		model.addAttribute("tagList", tagService.findTagByPostId(id));

		return "details";
	}

	@RequestMapping(value = "/category/{slugTerm}", method = RequestMethod.GET)
	public String indexByCategory(@PathVariable("slugTerm") String slugTerm, SearchCriteria searchCriteria, Model model)
			throws Exception {
		Set<Long> categoryLevelSet = makeCategoryLevelSet(slugTerm);
		
		List<PostVO> list = postService.findListByCategory(categoryLevelSet, searchCriteria);

		int countCriteria = postService.countCriteriaByCategory(slugTerm, searchCriteria);

		return makeIndex(list, searchCriteria, countCriteria, model);
	}
	
	private Set<Long> makeCategoryLevelSet(String slugTerm) throws Exception {
		List<CategoryLevel> listCategoryLevel = categoryService.listCategoryLevel(slugTerm);
		
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
	
	@RequestMapping(value = "/tag", method = RequestMethod.GET)
	public String tag(Model model) throws Exception {
		model.addAttribute("tagList", tagService.findTagList());
		
		return "tags";
	}
	
	@RequestMapping(value = "/tag/{slugTerm}", method = RequestMethod.GET)
	public String tagBySlugTerm(@PathVariable("slugTerm") String slugTerm, SearchCriteria searchCriteria, Model model) throws Exception {
		List<PostVO> list = postService.findListByTag(slugTerm, searchCriteria);
		
		int countCriteria = postService.countCriteriaByTag(slugTerm, searchCriteria);
		
		LOGGER.info("tag count: " + countCriteria);
		
		return makeIndex(list, searchCriteria, countCriteria, model);
	}
	
	private String makeIndex(List<PostVO> list, SearchCriteria searchCriteria, int countCriteria, Model model) {
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(searchCriteria);
		pageMaker.setTotalCount(countCriteria);

		model.addAttribute("pageMaker", pageMaker);

		return "index";
	}

}
