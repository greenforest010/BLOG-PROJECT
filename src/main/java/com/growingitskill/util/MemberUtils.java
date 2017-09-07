package com.growingitskill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.growingitskill.domain.MemberVO;
import com.growingitskill.service.MemberService;

@Component
public class MemberUtils {
	
	@Autowired
	private MemberService memberService;
	
	public void makeMemberModel(Model model, String loginId) throws Exception {
		MemberVO memberVO = memberService.findMemberByLoginId(loginId);

		model.addAttribute(memberVO);
	}
	
	public long getMemberIdByLoginId(String loginId) throws Exception {
		return memberService.findMemberIdByLoginId(loginId);
	}
	
	public MemberVO getMemberByLoginId(String loginId) throws Exception {
		return memberService.findMemberByLoginId(loginId);
	}
	
	public void modifyMemberByLoginId(MemberVO memberVO) throws Exception {
		memberService.modifyMemberByLoginId(memberVO);
	}

}
