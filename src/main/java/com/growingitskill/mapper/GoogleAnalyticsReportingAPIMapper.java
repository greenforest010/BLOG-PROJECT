package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.growingitskill.domain.GoogleAnalyticsReportingAPIVO;

public interface GoogleAnalyticsReportingAPIMapper {

	@Select("SELECT * FROM google_analytics_reporting_api WHERE api_name LIKE #{apiName}")
	@Results({ @Result(property = "apiName", column = "api_name"),
			@Result(property = "keyFileLocation", column = "key_file_location"),
			@Result(property = "viewId", column = "view_id") })
	GoogleAnalyticsReportingAPIVO readGoogleAnalyticsReportingAPIByApiName(String apiName) throws Exception;

}
