package com.growingitskill.service;

import java.util.List;
import java.util.Set;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;

public interface PostService {

	void regist(PostVO postVO) throws Exception;

	PostVO findById(long id) throws Exception;

	void modify(PostVO postVO) throws Exception;

	void removeByIds(long[] ids) throws Exception;

	List<PostVO> listAll() throws Exception;
	
	List<PostVO> findList(SearchCriteria searchCriteria) throws Exception;
	
	int countCriteria(SearchCriteria searchCriteria) throws Exception;
	
	List<PostVO> findListByCategory(Set<Long> categoryLevelSet, SearchCriteria searchCriteria) throws Exception;
	
	int countCriteriaByCategory(String slugTerm, SearchCriteria searchCriteria) throws Exception;
	
	List<PostVO> findListByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception;
	
	int countCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception;

}
