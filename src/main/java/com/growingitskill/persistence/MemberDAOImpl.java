package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.MemberVO;
import com.growingitskill.mapper.MemberMapper;

public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberMapper {

	private static String namespace = "com.growingitskill.mapper.MemberMapper";

	@Override
	public long readMemberIdByLoginId(String loginId) throws Exception {
		return getSqlSession().selectOne(namespace + ".readMemberIdByLoginId", loginId);
	}
	
	@Override
	public MemberVO readMemberById(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readMemberById", id);
	}

	@Override
	public MemberVO readMemberByLoginId(String loginId) throws Exception {
		return getSqlSession().selectOne(namespace + ".readMemberByLoginId", loginId);
	}
	
	@Override
	public MemberVO readMemberByAdminRole() throws Exception {
		return getSqlSession().selectOne(namespace + ".readMemberByRole");
	}

	@Override
	public void updateMemberByLoginId(MemberVO memberVO) {
		getSqlSession().update(namespace + ".updateMemberByLoginId" + memberVO);
	}

}
