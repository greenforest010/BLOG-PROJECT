package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
	
	@Select("SELECT id FROM member WHERE login_id LIKE #{loginId}")
	long readIdByLoginId(String loginId) throws Exception;

}
