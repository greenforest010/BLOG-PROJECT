package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.growingitskill.domain.NaverPapagoNMT;
import com.growingitskill.domain.OpenApi;

public interface OpenApiMapper {

	@Select("SELECT * FROM openapi WHERE api_name LIKE #{apiName}")
	@Results({ @Result(property = "apiName", column = "api_name"), @Result(property = "clientId", column = "client_id"),
			@Result(property = "clientSecret", column = "client_secret") })
	NaverPapagoNMT readNaverPapagoNMTByApiName(String apiName) throws Exception;

}
