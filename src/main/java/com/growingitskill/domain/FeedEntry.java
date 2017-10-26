package com.growingitskill.domain;

import java.util.Date;
import java.util.List;

/**
 * 
 * @description Atom Entry Element 형식
 *
 */
public class FeedEntry {

	// required
	private String id;
	private String title;
	private Date updated;
	
	// recommended
	private String link;
	
	// optional
	private Date published;

	private List<TagVO> tagVOs;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Date getPublished() {
		return published;
	}
	
	public void setPublished(Date published) {
		this.published = published;
	}

	public List<TagVO> getTagVOs() {
		return tagVOs;
	}

	public void setTagVOs(List<TagVO> tagVOs) {
		this.tagVOs = tagVOs;
	}

}
