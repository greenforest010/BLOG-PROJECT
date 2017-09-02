package com.growingitskill.domain;

public class NaverPapagoNMT extends OpenApi {

	private String clientId;
	private String clientSecret;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Override
	public String toString() {
		return "NaverPapagoNMT[" + super.getApiName() + ", " + super.getUrl() + ", " + super.getVersion() + ", "
				+ super.getProvider() + ", " + getClientId() + ", " + getClientSecret() + "]";
	}

}
