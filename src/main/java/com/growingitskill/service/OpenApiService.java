package com.growingitskill.service;

import com.growingitskill.domain.NaverPapagoNMT;

public interface OpenApiService {
	
	NaverPapagoNMT findByApiName(String apiName) throws Exception;

}
