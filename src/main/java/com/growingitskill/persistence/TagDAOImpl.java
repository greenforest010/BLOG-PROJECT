package com.growingitskill.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.TagVO;
import com.growingitskill.mapper.TagMapper;

public class TagDAOImpl extends SqlSessionDaoSupport implements TagMapper {
	
	private static final String namaspace = "com.growingitskill.mapper.TagMapper";

	@Override
	public List<TagVO> listAll() throws Exception {
		return getSqlSession().selectList(namaspace + ".listAll");
	}

	@Override
	public void createTag(TagVO tagVO) throws Exception {
		getSqlSession().insert(namaspace + ".createTag", tagVO);
	}

	@Override
	public TagVO readTagById(long id) throws Exception {
		return getSqlSession().selectOne(namaspace + ".readTagById", id);
	}

	@Override
	public void updateTermById(long id, String term) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("term", term);
		
		getSqlSession().update(namaspace + ".updateTermById", map);
	}

	@Override
	public void updateSlugTermById(long id, String slugTerm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("slugTerm", slugTerm);
		
		getSqlSession().update(namaspace + ".updateSlugTermById", map);
	}

	@Override
	public void deleteTagById(long id) throws Exception {
		getSqlSession().delete(namaspace + "deleteTagById", id);
	}

}
