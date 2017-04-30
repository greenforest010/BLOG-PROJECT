package com.growingitskill.domain;

import java.util.Date;

public class PostVO {

	private long id;
	private String title;
	private Date datetime;
	private long author;
	private String content;
	private String slug_title;
	private Date published;

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

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
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

	public String getSlug_title() {
		return slug_title;
	}

	public void setSlug_title(String slug_title) {
		this.slug_title = slug_title;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

}
