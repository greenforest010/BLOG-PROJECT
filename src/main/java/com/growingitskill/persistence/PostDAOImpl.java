package com.growingitskill.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
import com.growingitskill.mapper.PostMapper;

public class PostDAOImpl extends SqlSessionDaoSupport implements PostMapper {
	
	private static String namespace = "com.growingitskill.mapper.PostMapper";

	@Override
	public void create(PostVO vo) throws Exception {
		getSqlSession().insert(namespace + ".create", vo);
	}

	@Override
	public PostVO read(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".read", id);
	}

	@Override
	public void update(PostVO vo) throws Exception {
		getSqlSession().update(namespace + ".update", vo);
	}

	@Override
	public void delete(long[] postId) throws Exception {
		getSqlSession().delete(namespace + ".delete", postId);
	}

	@Override
	public List<PostVO> listAll() throws Exception {
		return getSqlSession().selectList(namespace + ".listAll");
	}

}
