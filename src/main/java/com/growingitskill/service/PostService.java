package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.PostVO;

public interface PostService {

	void regist(PostVO postVO) throws Exception;

	PostVO findById(long id) throws Exception;

	void modify(PostVO postVO) throws Exception;

	void removeByIds(long[] ids) throws Exception;

	List<PostVO> listAll() throws Exception;

}
