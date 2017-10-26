package com.growingitskill.domain;

public class BlogInfo {

	private String title;
	private String subtitle;
	private String domainName;
	private String rights;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDomainName() {
		return domainName;
	}
	
	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return "BlogInfo [" + "title: " + getTitle() + ", subtitle: " + getSubtitle() + ", domainName: "
				+ getDomainName() + ", rights: " + getRights() + "]";
	}

}
