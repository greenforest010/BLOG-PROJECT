package com.growingitskill.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CategoryVO> listAll() throws Exception {
		List<CategoryVO> list = categoryService.listAll();
		
		return list;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public CategoryVO register(CategoryVO categoryVO) throws Exception {
		categoryService.addCategory(categoryVO);
		
		return categoryVO;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void rename(@PathVariable long id,  @RequestBody CategoryVO categoryVO) throws Exception {
		categoryVO.setId(id);
		
		logger.info(categoryVO.toString());
		
		if (categoryVO.getTerm() != null) {
			categoryService.renameCategory(categoryVO);
		}
		
		if (categoryVO.getSlugTerm() != null) {
			categoryService.modifyCategorySlugTerm(categoryVO);
		}
		
		if (categoryVO.getParent() != 0) {
			categoryService.moveCategory(categoryVO);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void remove(@PathVariable long id) throws Exception {
		categoryService.removeCategory(id);
	}

}
