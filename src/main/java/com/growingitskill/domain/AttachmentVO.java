package com.growingitskill.domain;

import java.util.Date;

public class AttachmentVO {
	private long id;
	private String fullName;
	private Date registered;
	private Date updated;
	private String mimeType;
	private String alternateText;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getAlternateText() {
		return alternateText;
	}

	public void setAlternateText(String alternateText) {
		this.alternateText = alternateText;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Attachment[" + "id: " + getId() + ", fullname: " + getFullName() + ", registered: " + getRegistered()
				+ ", updated: " + getUpdated() + ", mimeType: " + getMimeType() + ", alternateText: "
				+ getAlternateText() + ", description: " + getDescription() + "]";

	}

}
