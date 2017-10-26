package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.BlogInfo;
import com.growingitskill.mapper.BlogInfoMapper;

public class BlogInfoImpl extends SqlSessionDaoSupport implements BlogInfoMapper {
	
	private final String namespace = "com.grwoingitskill.mapper.BlogInfoMapper";

	@Override
	public BlogInfo readBlogInfo() throws Exception {
		return getSqlSession().selectOne(namespace + ".readBlogInfo");
	}

	@Override
	public void updateBlogInfo(BlogInfo blogInfo) throws Exception {
		getSqlSession().update(namespace + ".updateBlogInfo", blogInfo);
	}

}
