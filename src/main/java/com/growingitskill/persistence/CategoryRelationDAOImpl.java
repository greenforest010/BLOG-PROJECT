package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
import com.growingitskill.mapper.CategoryRelationMapper;

public class CategoryRelationDAOImpl extends SqlSessionDaoSupport implements CategoryRelationMapper {

	private static final String namespace = "com.growingitskill.mapper.CategoryRelationMapper";
	
	@Override
	public void createCategoryRelation(PostVO postVO) throws Exception {
		getSqlSession().insert(namespace + ".createCategoryRelation", postVO);
	}

	@Override
	public void updateCategoryRelation(PostVO postVO) throws Exception {
		getSqlSession().update(namespace + ".updateCategoryRelation", postVO);
	}

}
