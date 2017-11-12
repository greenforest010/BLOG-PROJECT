package com.growingitskill.service;

import com.growingitskill.domain.MemberVO;

public interface MemberService {
	
	long findMemberIdByLoginId(String loginId) throws Exception;
	
	MemberVO findMemberById(long id) throws Exception;
	
	MemberVO findMemberByLoginId(String loginId) throws Exception;
	
	MemberVO findMemberByAdminRole() throws Exception;
	
	void modifyMemberByLoginId(MemberVO memberVO) throws Exception;
	
}
