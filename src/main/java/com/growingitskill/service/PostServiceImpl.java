package com.growingitskill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.PostVO;
import com.growingitskill.mapper.CategoryRelationMapper;
import com.growingitskill.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private CategoryRelationMapper categoryRelationMapper;

	@Override
	public void regist(PostVO postVO) throws Exception {
		postMapper.create(postVO);
		
		System.out.println("post getID: " + postVO.getId() + ", category getID: " + postVO.getCategoryVO().getId());
		
		categoryRelationMapper.create(postVO);
	}

	@Override
	public PostVO read(long id) throws Exception {
		return postMapper.read(id);
	}

	@Override
	public void modify(PostVO vo) throws Exception {
		postMapper.update(vo);
	}

	@Override
	public void remove(long[] postId) throws Exception {
		postMapper.delete(postId);
	}

	@Override
	public List<PostVO> listAll() throws Exception {
		return postMapper.listAll();
	}
	
	

}
