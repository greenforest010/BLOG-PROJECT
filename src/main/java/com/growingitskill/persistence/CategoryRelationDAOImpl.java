package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
import com.growingitskill.mapper.CategoryRelationMapper;

public class CategoryRelationDAOImpl extends SqlSessionDaoSupport implements CategoryRelationMapper {

	private static final String namespace = "com.growingitskill.mapper.CategoryRelationMapper";
	
	@Override
	public void create(PostVO postVO) throws Exception {
		getSqlSession().insert(namespace + ".create", postVO);
	}

}
