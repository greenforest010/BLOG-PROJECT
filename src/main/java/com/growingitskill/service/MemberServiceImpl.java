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
	public long findIdByLoginId(String loginId) throws Exception {
		return memberMapper.readIdByLoginId(loginId);
	}

	@Override
	public MemberVO findMemberByLoginId(String loginId) throws Exception {
		return memberMapper.readMemberByLoginId(loginId);
	}

	@Override
	public void modifyMemberByLoginId(MemberVO memberVO) throws Exception {
		memberMapper.updateMemberByLoginId(memberVO);
	}
	
	

}
