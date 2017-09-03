package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AboutMapper {
	
	@Select("SELECT * FROM about")
	String read() throws Exception;
	
	@Update("UPDATE about SET content = #{content}")
	void update(String content) throws Exception;

}
