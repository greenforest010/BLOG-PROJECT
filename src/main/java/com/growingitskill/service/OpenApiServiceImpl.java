package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.NaverPapagoNMT;
import com.growingitskill.mapper.OpenApiMapper;

@Service
public class OpenApiServiceImpl implements OpenApiService {
	
	@Autowired
	private OpenApiMapper openApiMapper;

	@Override
	public NaverPapagoNMT findNaverPapagoNMTByApiName(String apiName) throws Exception {
		return openApiMapper.readNaverPapagoNMTByApiName(apiName);
	}

}
