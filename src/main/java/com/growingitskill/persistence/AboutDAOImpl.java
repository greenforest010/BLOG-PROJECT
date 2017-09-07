package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.mapper.AboutMapper;

public class AboutDAOImpl extends SqlSessionDaoSupport implements AboutMapper {
	
	private final String namespace = "com.growingitskill.mapper.AboutMapper";

	@Override
	public String readAbout() throws Exception {
		return getSqlSession().selectOne(namespace + ".read");
	}

	@Override
	public void updateAboutContent(String content) throws Exception {
		getSqlSession().update(namespace + ".updateAboutContent", content);
	}

}
