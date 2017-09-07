package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AboutMapper {
	
	@Select("SELECT * FROM about")
	String readAbout() throws Exception;
	
	@Update("UPDATE about SET content = #{content}")
	void updateAboutContent(String content) throws Exception;

}
