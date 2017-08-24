package com.growingitskill.domain;

public class TagVO {

	private long id;
	private String term;
	private String scheme;
	private String label;
	private String slugTerm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSlugTerm() {
		return slugTerm;
	}

	public void setSlugTerm(String slugTerm) {
		this.slugTerm = slugTerm;
	}

	@Override
	public String toString() {
		return "Tag[id: " + getId() + ", term: " + getTerm() + ", scheme: " + getScheme() + ", label: " + getLabel()
				+ ", slugTerm: " + getSlugTerm() + "]";
	}

}
