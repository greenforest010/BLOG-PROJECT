package com.growingitskill.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.mapper.PostMapper;

public class PostDAOImpl extends SqlSessionDaoSupport implements PostMapper {
	
	private static String namespace = "com.growingitskill.mapper.PostMapper";

	@Override
	public void create(PostVO postVO) throws Exception {
		getSqlSession().insert(namespace + ".create", postVO);
	}

	@Override
	public PostVO readById(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readById", id);
	}

	@Override
	public void update(PostVO postVO) throws Exception {
		getSqlSession().update(namespace + ".update", postVO);
	}

	@Override
	public void deleteByIds(long[] ids) throws Exception {
		getSqlSession().delete(namespace + ".deleteByIds", ids);
	}

	@Override
	public List<PostVO> listAll() throws Exception {
		return getSqlSession().selectList(namespace + ".listAll");
	}
	
	@Override
	public List<PostVO> readList(SearchCriteria searchCriteria) throws Exception {
		return getSqlSession().selectList(namespace + ".readList", searchCriteria);
	}
	
	@Override
	public int countPaging(SearchCriteria searchCriteria) throws Exception {
		return getSqlSession().selectOne(namespace + ".countPaging", searchCriteria);
	}

	@Override
	public List<PostVO> readListByCategory(Set<Long> categoryLevelSet, SearchCriteria searchCriteria) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("categoryLevelSet", categoryLevelSet);
		map.put("criteria", searchCriteria);
		
		return getSqlSession().selectList(namespace + ".readListByCategory", map);
	}

	@Override
	public int countPagingByCategory(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("slugTerm", slugTerm);
		map.put("criteria", searchCriteria);
		
		return getSqlSession().selectOne(namespace + ".countPagingByCategory", map);
	}

}
