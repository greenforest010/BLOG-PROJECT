package com.growingitskill.domain;

import java.net.URL;
import java.util.List;

/**
 * 
 * @description Atom Feed Element 형식
 *
 */
public class FeedInfo {

	// required
	private String id;
	private String title;
	
	// recommended
	private String author;
	private URL link;
	
	// optional
	private String subtitle;
	private String rights;
	
	private List<FeedEntry> feedEntries;

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
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public URL getLink() {
		return link;
	}
	
	public void setLink(URL link) {
		this.link = link;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public List<FeedEntry> getFeedEntries() {
		return feedEntries;
	}

	public void setFeedEntries(List<FeedEntry> feedEntries) {
		this.feedEntries = feedEntries;
	}	

}
