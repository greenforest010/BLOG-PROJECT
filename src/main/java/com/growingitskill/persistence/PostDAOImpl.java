package com.growingitskill.persistence;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
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

}
