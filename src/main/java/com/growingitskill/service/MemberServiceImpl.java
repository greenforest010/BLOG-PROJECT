package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.MemberVO;
import com.growingitskill.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public long getById(MemberVO memberVO) throws Exception {
		return memberMapper.getById(memberVO);
	}
	
	

}
