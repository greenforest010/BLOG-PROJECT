package com.growingitskill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryVO> listAll() throws Exception {
		return categoryMapper.listAll();
	}
	
	@Override
	public void addCategory(CategoryVO categoryVO) throws Exception {
		categoryMapper.create(categoryVO);
	}

	@Override
	public void renameCategoryById(long id, String term) throws Exception {
		categoryMapper.updateTermById(id, term);
	}

	@Override
	public void modifyCategorySlugTermById(long id, String slugTerm) throws Exception {
		categoryMapper.updateSlugTermById(id, slugTerm);
	}
	
	@Override
	public void moveCategory(long id, long parent) throws Exception {
		categoryMapper.updateParentById(id, parent);
	}
	
	@Override
	public void removeCategory(long id) throws Exception {
		categoryMapper.deleteById(id);
	}

}
