package com.growingitskill.service;

import com.growingitskill.domain.BlogInfo;

public interface BlogInfoService {
	
	BlogInfo findBlogInfo() throws Exception;
	
	void modifyBlogInfo(BlogInfo blogInfo) throws Exception;

}
