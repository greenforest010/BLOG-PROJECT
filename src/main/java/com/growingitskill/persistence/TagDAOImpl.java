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
	public List<TagVO> readTags() throws Exception {
		return getSqlSession().selectList(namaspace + ".readTags");
	}

	@Override
	public void createTag(TagVO tagVO) throws Exception {
		getSqlSession().insert(namaspace + ".createTag", tagVO);
	}
	
	@Override
	public void createTags(List<TagVO> tags) throws Exception {
		getSqlSession().insert(namaspace + ".createTags", tags);
	}

	@Override
	public TagVO readTagById(long id) throws Exception {
		return getSqlSession().selectOne(namaspace + ".readTagById", id);
	}
	
	@Override
	public TagVO readTagBySlugTerm(String slugTerm) throws Exception {
		return getSqlSession().selectOne(namaspace + ".readTagBySlugTerm", slugTerm);
	}
	
	@Override
	public List<TagVO> readTagByPostId(long postId) throws Exception {
		return getSqlSession().selectList(namaspace + ".readTagByPostId", postId);
	}
	
	@Override
	public List<String> readTagTermByPostId(long postId) throws Exception {
		return getSqlSession().selectList(namaspace + ".readTagTermByIds", postId);
	}
	
	@Override
	public List<Long> readTagIdByTerms(String[] terms) throws Exception {
		return getSqlSession().selectList(namaspace + ".readTagIdByTerms", terms);
	}
	
	@Override
	public List<String> readTagTermByTerms(String[] terms) throws Exception {
		return getSqlSession().selectList(namaspace + ".readTagsByTerms", terms);
	}

	@Override
	public void updateTagTermAndSlugTermById(long id, String term, String slugTerm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("term", term);
		map.put("slugTerm", slugTerm);
		
		getSqlSession().update(namaspace + ".updateTagTermAndSlugTermById", map);
	}

	@Override
	public void updateTagSlugTermById(long id, String slugTerm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("slugTerm", slugTerm);
		
		getSqlSession().update(namaspace + ".updateTagSlugTermById", map);
	}

	@Override
	public void deleteTagById(long id) throws Exception {
		getSqlSession().delete(namaspace + "deleteTagById", id);
	}

}
