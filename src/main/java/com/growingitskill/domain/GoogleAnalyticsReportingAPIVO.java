package com.growingitskill.domain;

public class GoogleAnalyticsReportingAPIVO {

	private String apiName;
	private String version;
	private String provider;
	private String keyFileLocation;
	private String viewId;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getKeyFileLocation() {
		return keyFileLocation;
	}

	public void setKeyFileLocation(String keyFileLocation) {
		this.keyFileLocation = keyFileLocation;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	@Override
	public String toString() {
		return "GoogleAnalyticsReportingAPI[" + "apiName: " + getApiName() + ", version: " + getVersion()
				+ ", provider: " + getProvider() + ", keyFileLocation: " + getKeyFileLocation() + ", viewId: "
				+ getViewId() + "]";
	}

}
