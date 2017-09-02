package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.NaverPapagoNMT;
import com.growingitskill.mapper.OpenApiMapper;

public class OpenApiDAOImpl extends SqlSessionDaoSupport implements OpenApiMapper{
	
	private final String namespace = "com.growingitskill.mapper.OpenApiMapper";

	@Override
	public NaverPapagoNMT readByApiName(String apiName) throws Exception {
		return getSqlSession().selectOne(namespace + ".readByApiName", apiName);
	}
	
}
