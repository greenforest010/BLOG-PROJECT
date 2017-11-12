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
	public long findMemberIdByLoginId(String loginId) throws Exception {
		return memberMapper.readMemberIdByLoginId(loginId);
	}
	
	@Override
	public MemberVO findMemberById(long id) throws Exception {
		return memberMapper.readMemberById(id);
	}

	@Override
	public MemberVO findMemberByLoginId(String loginId) throws Exception {
		return memberMapper.readMemberByLoginId(loginId);
	}
	
	@Override
	public MemberVO findMemberByAdminRole() throws Exception {
		return memberMapper.readMemberByAdminRole();
	}

	@Override
	public void modifyMemberByLoginId(MemberVO memberVO) throws Exception {
		memberMapper.updateMemberByLoginId(memberVO);
	}

}
