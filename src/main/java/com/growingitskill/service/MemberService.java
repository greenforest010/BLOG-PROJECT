package com.growingitskill.service;

import com.growingitskill.domain.MemberVO;

public interface MemberService {
	
	long findIdByLoginId(String loginId) throws Exception;
	
	MemberVO findMemberByLoginId(String loginId) throws Exception;
	
	void modifyMemberByLoginId(MemberVO memberVO) throws Exception;
	
}
