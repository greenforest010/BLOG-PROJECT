package com.growingitskill.domain;

import javax.validation.constraints.Size;

public class MemberVO {
	private long id;
	private String loginId;
	
	@Size(min=2)
	private String nickname;
	
	@Size(min=2)
	private String displayName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "Member[" + "id: " + id + ", loginId: " + loginId + ", nickname: " + nickname + ", displayName: "
				+ displayName + "]";
	}

}
