package com.growingitskill.service;

import com.growingitskill.domain.NaverPapagoNMT;

public interface OpenApiService {
	
	NaverPapagoNMT findNaverPapagoNMTByApiName(String apiName) throws Exception;

}
