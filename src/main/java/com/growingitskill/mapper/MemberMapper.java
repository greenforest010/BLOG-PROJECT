package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.MemberVO;

public interface MemberMapper {

	@Select("SELECT id FROM member WHERE login_id LIKE #{loginId}")
	long readIdByLoginId(String loginId) throws Exception;

	@Select("SELECT * FROM member WHERE login_id LIKE #{loginId}")
	@Results({ @Result(property = "loginId", column = "login_id"),
			@Result(property = "displayName", column = "display_name") })
	MemberVO readMemberByLoginId(String loginId) throws Exception;

	@Update("UPDATE member SET nickname = #{nickname}, display_name = #{displayName} WHERE login_id LIKE #{loginId}")
	void updateMemberByLoginId(MemberVO memberVO);

}
