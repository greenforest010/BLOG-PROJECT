package com.growingitskill.domain;

public class CategoryVO {

	private long id;
	private String term;
	private String scheme;
	private String label;
	private String slugTerm;
	private long parent;

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

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

}
