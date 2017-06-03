package com.growingitskill.domain;

import java.util.Date;

public class PostVO {

	private long id;
	private String title;
	private Date updated;
	private long author;
	private String content;
	private String slugTitle;
	private Date published;

	private MemberVO memberVO;
	private CategoryVO categoryVO;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSlugTitle() {
		return slugTitle;
	}

	public void setSlugTitle(String slugTitle) {
		this.slugTitle = slugTitle;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	@Override
	public String toString() {
		String post = "id: " + getId() + ", title: " + getTitle() + ", author: " + getAuthor() + ", content: "
				+ getContent() + ", published: " + getPublished() + ", slugTitle: " + getSlugTitle();

		String member = null;
		String category = null;

		if (getMemberVO() != null) {
			member = ", loginId: " + getMemberVO().getLoginId();
		}

		if (getCategoryVO() != null) {
			category = ", categoryId: " + getCategoryVO().getId() + ", term: " + getCategoryVO().getTerm();
		}
		
		if (member != null && category != null) {
			return post + member + category;
		} else if (member != null && category == null) {
			return post + member;
		} else if (member == null && category != null) {
			return post + category;
		} else {
			return post;
		}
	}

}
