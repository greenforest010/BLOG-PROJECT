package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.GoogleAnalyticsReportingAPIVO;
import com.growingitskill.mapper.GoogleAnalyticsReportingAPIMapper;

@Service
public class GoogleAnalyticsReportingAPIServiceImpl implements GoogleAnalyticsReportingAPIService {

	@Autowired
	private GoogleAnalyticsReportingAPIMapper googleAnalyticsReportingAPIMapper;

	@Override
	public GoogleAnalyticsReportingAPIVO findGoogleAnalyticsReportingAPI(String apiName) throws Exception {
		return googleAnalyticsReportingAPIMapper.readGoogleAnalyticsReportingAPIByApiName(apiName);
	}

}
