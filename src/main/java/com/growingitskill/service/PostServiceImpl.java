package com.growingitskill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.PostVO;
import com.growingitskill.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostMapper postMapper;

	@Override
	public void regist(PostVO vo) throws Exception {
		postMapper.create(vo);
	}

	@Override
	public PostVO read(Long id) throws Exception {
		return postMapper.read(id);
	}

	@Override
	public void modify(PostVO vo) throws Exception {
		postMapper.update(vo);
	}

	@Override
	public void remove(Long id) throws Exception {
		postMapper.delete(id);
	}

	@Override
	public List<PostVO> listAll() throws Exception {
		return postMapper.listAll();
	}
	
	

}
