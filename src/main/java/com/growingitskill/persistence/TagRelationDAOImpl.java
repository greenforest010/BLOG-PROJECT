package com.growingitskill.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
import com.growingitskill.mapper.TagRelationMapper;

public class TagRelationDAOImpl extends SqlSessionDaoSupport implements TagRelationMapper {

	private static final String namespace = "com.growingitskill.mapper.TagRelationMapper";

	@Override
	public List<Long> readTagsByPostId(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readTagsByPostId", id);
	}

	@Override
	public void createTagRelation(PostVO postVO) throws Exception {
		getSqlSession().insert(namespace + ".createTagRelation" + postVO);
	}

	@Override
	public void deleteTagRelationByPostId(long postId) throws Exception {
		getSqlSession().delete(namespace + ".deleteTagRelationByPostId", postId);
	}

}
