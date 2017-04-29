package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.growingitskill.domain.User;

public interface UserMapper {
	
	@Select("select * from tblTest where tname = #{tname}")
	User getUser(@Param("tname") String tname);
	
	@Insert("insert into tblTest(tname) values(#{tname})")
	void setUser(@Param("tname") String tname);

}
