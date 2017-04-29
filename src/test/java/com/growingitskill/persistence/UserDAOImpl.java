package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.User;
import com.growingitskill.mapper.UserMapper;

// SqlSessionDaoSupport 클래스로 인해 SqlSession을 주입할 필요없이 getSqlSession() 사용 가능
public class UserDAOImpl extends SqlSessionDaoSupport implements UserMapper {
	
	private final static String namespace = "com.growingitskill.mapper.UserMapper";
	
	@Override
	public User getUser(String tname) {
		return getSqlSession().selectOne(namespace + ".getUser", tname);
	}
	
	@Override
	public void setUser(String tname) {
		getSqlSession().insert(namespace + ".setUser", tname);
	}
}
