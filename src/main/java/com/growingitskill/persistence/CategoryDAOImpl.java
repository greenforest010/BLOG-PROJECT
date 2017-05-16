package com.growingitskill.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.mapper.CategoryMapper;

public class CategoryDAOImpl extends SqlSessionDaoSupport implements CategoryMapper {
	
	private static final String namespace = "com.growingitskill.mapper.CategoryMapper";
	
	@Override
	public List<CategoryVO> listAll() throws Exception {
		return getSqlSession().selectList(namespace + ".listAll");
	}

}
