package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.PostVO;

public interface PostService {

	void regist(PostVO postVO) throws Exception;

	PostVO findById(long id) throws Exception;

	void modify(PostVO postVO) throws Exception;

	void removeByIds(long[] ids) throws Exception;

	List<PostVO> listAll() throws Exception;
	
	List<PostVO> findList(Criteria criteria) throws Exception;
	
	int countCriteria(Criteria criteria) throws Exception;

}
