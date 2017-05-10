package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Select;

import com.growingitskill.domain.MemberVO;

public interface MemberMapper {
	
	@Select("SELECT id FROM member WHERE login_id LIKE #{loginId}")
	long getById(MemberVO memberVO) throws Exception;

}
