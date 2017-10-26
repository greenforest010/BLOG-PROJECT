package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.BlogInfo;

public interface BlogInfoMapper {

	@Select("SELECT * FROM blog_info")
	@Results(@Result(property = "domainName", column = "domain_name"))
	BlogInfo readBlogInfo() throws Exception;

	@Update("UPDATE blog_info SET title = #{title}, subtitle = #{subtitle}, rights = #{rights} ")
	void updateBlogInfo(BlogInfo blogInfo) throws Exception;

}
