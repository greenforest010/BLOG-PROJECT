package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.BlogInfo;
import com.growingitskill.mapper.BlogInfoMapper;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {
	
	@Autowired
	private BlogInfoMapper blogInfoMapper;

	@Override
	public BlogInfo findBlogInfo() throws Exception {
		return blogInfoMapper.readBlogInfo();
	}

	@Override
	public void modifyBlogInfo(BlogInfo blogInfo) throws Exception {
		blogInfoMapper.updateBlogInfo(blogInfo);
	}

}
