package com.growingitskill.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.CategoryVO;
import com.growingitskill.mapper.CategoryMapper;

public class CategoryDAOImpl extends SqlSessionDaoSupport implements CategoryMapper {

	private static final String namespace = "com.growingitskill.mapper.CategoryMapper";

	@Override
	public List<CategoryVO> readCategories() throws Exception {
		return getSqlSession().selectList(namespace + ".readCategories");
	}

	@Override
	public void createCategory(CategoryVO categoryVO) throws Exception {
		getSqlSession().insert(namespace + ".createCategory", categoryVO);
	}

	@Override
	public CategoryVO readCategoryById(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readCategoryById", id);
	}

	@Override
	public CategoryVO readCategoryBySlugTerm(String slugTerm) throws Exception {
		return getSqlSession().selectOne(namespace + ".readCategoryBySlugTerm", slugTerm);
	}

	@Override
	public void updateCategoryTermAndSlugTermById(long id, String term, String slugTerm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("term", term);
		map.put("slugTerm", slugTerm);

		getSqlSession().update(namespace + ".updateCategoryTermAndSlugTermById", map);
	}

	@Override
	public void updateCategorySlugTermById(long id, String slugTerm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("slugTerm", slugTerm);

		getSqlSession().update(namespace + ".updateCategorySlugTermById", map);
	}

	@Override
	public void updateCategoryParentById(long id, long parent) throws Exception {
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
	public List<CategoryVO> readLeafCategories() throws Exception {
		return getSqlSession().selectList(namespace + ".readLeafCategories");
	}

	@Override
	public List<CategoryLevel> readCategoryLevel(String slugTerm) throws Exception {
		return getSqlSession().selectList(namespace + ".readCategoryLevel", slugTerm);
	}

}
