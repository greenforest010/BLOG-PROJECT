package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.mapper.TagRelationMapper;

@Service
public class TagRelationServiceImpl implements TagRelationService {
	
	@Autowired
	private TagRelationMapper tagRelationMapper;

	@Override
	public void removeByPostId(long postId) throws Exception {
		tagRelationMapper.deleteByPostId(postId);
	}

}
