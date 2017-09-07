package com.growingitskill.service;

import java.util.List;
import java.util.Set;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;

public interface PostService {

	void registPost(PostVO postVO) throws Exception;

	PostVO findPostById(long id) throws Exception;
	
	PostVO findPostBySlugTitle(String slugTitle) throws Exception;

	void modifyPost(PostVO postVO) throws Exception;

	void removePostByIds(long[] ids) throws Exception;

	List<PostVO> findPosts() throws Exception;

	List<PostVO> findPostsWithCriteria(SearchCriteria searchCriteria) throws Exception;

	int countPostWithCriteria(SearchCriteria searchCriteria) throws Exception;

	List<PostVO> findPostsWithCriteriaByCategory(Set<Long> categoryLevelSet, SearchCriteria searchCriteria) throws Exception;

	int countPostByCategory(Set<Long> categoryLevelSet) throws Exception;

	List<PostVO> findPostsWithCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception;

	int countPostWithCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception;

}
