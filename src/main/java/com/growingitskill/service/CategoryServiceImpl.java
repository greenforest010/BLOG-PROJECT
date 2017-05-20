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
	public void renameCategory(CategoryVO categoryVO) throws Exception {
		categoryMapper.updateTerm(categoryVO);
	}

	@Override
	public void modifyCategorySlugTerm(CategoryVO categoryVO) throws Exception {
		categoryMapper.updateSlugTerm(categoryVO);
	}
	
	@Override
	public void moveCategory(CategoryVO categoryVO) throws Exception {
		categoryMapper.updateParent(categoryVO);
	}
	
	@Override
	public void removeCategory(long id) throws Exception {
		categoryMapper.delete(id);
	}

}
