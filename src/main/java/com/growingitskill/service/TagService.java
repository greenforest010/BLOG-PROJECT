package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.TagVO;

public interface TagService {
	
	List<TagVO> findTagList() throws Exception;
	
	void addTag(TagVO tagVO) throws Exception;
	
	TagVO findTagById(long id) throws Exception;
	
	void modifyTermById(long id, String term) throws Exception;
	
	void modifySlugTermById(long id, String slugTerm) throws Exception;
	
	void removeTagById(long id) throws Exception;

}
