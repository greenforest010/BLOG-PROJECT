package com.growingitskill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.CategoryVO;
import com.growingitskill.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryVO> findCategories() throws Exception {
		return categoryMapper.readCategories();
	}

	@Override
	public CategoryVO findCategoryById(long id) throws Exception {
		return categoryMapper.readCategoryById(id);
	}

	@Override
	public CategoryVO findCategoryBySlugTerm(String slugTerm) throws Exception {
		return categoryMapper.readCategoryBySlugTerm(slugTerm);
	}

	@Override
	public void addCategory(CategoryVO categoryVO) throws Exception {
		categoryMapper.createCategory(categoryVO);
	}

	@Override
	public void renameCategoryById(long id, String term, String slugTerm) throws Exception {
		categoryMapper.updateCategoryTermAndSlugTermById(id, term, slugTerm);
	}

	@Override
	public void modifyCategorySlugTermById(long id, String slugTerm) throws Exception {
		categoryMapper.updateCategorySlugTermById(id, slugTerm);
	}

	@Override
	public void moveCategory(long id, long parent) throws Exception {
		categoryMapper.updateCategoryParentById(id, parent);
	}

	@Override
	public void removeCategoryById(long id) throws Exception {
		categoryMapper.deleteCategoryById(id);
	}

	@Override
	public List<CategoryVO> findLeafCategories() throws Exception {
		return categoryMapper.readLeafCategories();
	}

	@Override
	public List<CategoryLevel> findCategoryLevel(String slugTerm) throws Exception {
		return categoryMapper.readCategoryLevel(slugTerm);
	}

}
