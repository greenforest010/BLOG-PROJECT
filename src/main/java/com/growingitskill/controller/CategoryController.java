package com.growingitskill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.service.CategoryService;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<CategoryVO> listAll() throws Exception {
		List<CategoryVO> list = categoryService.listAll();
		
		return list;
	}

}
