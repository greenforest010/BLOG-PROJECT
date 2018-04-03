package com.growingitskill.persistence;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.growingitskill.domain.GoogleAnalyticsReportingAPIVO;
import com.growingitskill.mapper.GoogleAnalyticsReportingAPIMapper;

public class GoogleAnalyticsReportingAPIDAOImpl extends SqlSessionDaoSupport
		implements GoogleAnalyticsReportingAPIMapper {

	private static final String namespace = "com.growingitskill.mapper.GoogleAnalyticsReportingAPIMapper";

	@Override
	public GoogleAnalyticsReportingAPIVO readGoogleAnalyticsReportingAPIByApiName(String apiName) throws Exception {
		return getSqlSession().selectOne(namespace + ".readGoogleAnalyticsReportingAPIByApiName", apiName);
	}

}
