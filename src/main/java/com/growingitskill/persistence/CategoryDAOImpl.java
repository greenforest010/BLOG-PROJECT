package com.growingitskill.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.mapper.CategoryMapper;

public class CategoryDAOImpl extends SqlSessionDaoSupport implements CategoryMapper {

	private static final String namespace = "com.growingitskill.mapper.CategoryMapper";

	@Override
	public List<CategoryVO> listAll() throws Exception {
		return getSqlSession().selectList(namespace + ".listAll");
	}

	@Override
	public void create(CategoryVO categoryVO) throws Exception {
		getSqlSession().insert(namespace + ".create", categoryVO);
	}

	@Override
	public CategoryVO readCategoryById(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readCategoryById", id);
	}

	@Override
	public void updateTermById(long id, String term) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("term", term);

		getSqlSession().update(namespace + ".updateTermById", map);
	}

	@Override
	public void updateSlugTermById(long id, String slugTerm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("slugTerm", slugTerm);

		getSqlSession().update(namespace + ".updateSlugTermById", map);
	}

	@Override
	public void updateParentById(long id, long parent) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("parent", parent);

		getSqlSession().update(namespace + ".updateParentById", map);
	}

	@Override
	public void deleteCategoryById(long id) throws Exception {
		getSqlSession().delete(namespace + ".deleteById", id);
	}

	@Override
	public List<CategoryVO> listLeafCategory() throws Exception {
		return getSqlSession().selectList(namespace + ".listLeafCategory");
	}

}
