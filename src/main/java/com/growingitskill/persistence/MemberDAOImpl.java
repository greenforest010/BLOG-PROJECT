package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.mapper.MemberMapper;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberMapper {
	
	private static String namespace = "com.growingitskill.mapper.MemberMapper";

	@Override
	public long readIdByLoginId(String loginId) throws Exception {
		return getSqlSession().selectOne(namespace + ".readIdByLoginId", loginId);
	}

}
