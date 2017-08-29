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
	public void create(PostVO postVO) throws Exception {
		getSqlSession().insert(namespace + ".create" + postVO);
	}

	@Override
	public void deleteByPostId(long postId) throws Exception {
		getSqlSession().delete(namespace + ".deleteByPostId", postId);
	}

}
