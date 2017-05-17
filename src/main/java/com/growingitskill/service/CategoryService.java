package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.CategoryVO;

public interface CategoryService {
	
	List<CategoryVO> listAll() throws Exception;
	
	void addCategory(CategoryVO categoryVO) throws Exception;
	
	void renameCategory(CategoryVO categoryVO) throws Exception;
	
	void modifyCategorySlugTerm(CategoryVO categoryVO) throws Exception;
	
	void removeCategory(long id) throws Exception;

}
