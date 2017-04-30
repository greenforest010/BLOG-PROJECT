package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.PostVO;

public interface PostService {

	void regist(PostVO vo) throws Exception;

	PostVO read(Long id) throws Exception;

	void modify(PostVO vo) throws Exception;

	void remove(Long id) throws Exception;

	List<PostVO> listAll() throws Exception;

}
