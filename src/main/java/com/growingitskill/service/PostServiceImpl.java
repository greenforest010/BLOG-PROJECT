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
		
		categoryRelationMapper.create(postVO);
	}

	@Override
	public PostVO findById(long id) throws Exception {
		return postMapper.readById(id);
	}

	@Override
	public void modify(PostVO postVO) throws Exception {
		postMapper.update(postVO);
		
		categoryRelationMapper.update(postVO);
	}

	@Override
	public void removeByIds(long[] ids) throws Exception {
		postMapper.deleteByIds(ids);
	}

	@Override
	public List<PostVO> listAll() throws Exception {
		return postMapper.listAll();
	}
	
	

}
