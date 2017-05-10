package com.growingitskill.service;

import com.growingitskill.domain.MemberVO;

public interface MemberService {
	
	long getById(MemberVO memberVO) throws Exception;
	
}
