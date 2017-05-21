package com.growingitskill.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.mapper.CategoryMapper;

public class CategoryDAOImpl extends SqlSessionDaoSupport implements CategoryMapper {
	
	private static final String namespace = "com.growingitskill.mapper.CategoryMapper";
	
	@Override
	public CategoryVO selectCategory(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".findCategory", id);
	}
	
	@Override
	public List<CategoryVO> listAll() throws Exception {
		return getSqlSession().selectList(namespace + ".listAll");
	}

	@Override
	public void create(CategoryVO categoryVO) throws Exception {
		getSqlSession().insert(namespace + ".create", categoryVO);
	}

	@Override
	public void updateTerm(CategoryVO categoryVO) throws Exception {
		getSqlSession().update(namespace + ".updateTerm", categoryVO);
	}

	@Override
	public void updateSlugTerm(CategoryVO categoryVO) throws Exception {
		getSqlSession().update(namespace + ".updateSlugTerm", categoryVO);
	}
	
	@Override
	public void updateParent(CategoryVO categoryVO) throws Exception {
		getSqlSession().update(namespace + ".updateParent", categoryVO);
	}
	
	@Override
	public void delete(long id) throws Exception {
		getSqlSession().delete(namespace + ".delete", id);
	}

}
