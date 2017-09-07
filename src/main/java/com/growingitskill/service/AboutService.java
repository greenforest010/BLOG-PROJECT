package com.growingitskill.service;

public interface AboutService {
	
	String findAbout() throws Exception;
	
	void modifyAboutContent(String content) throws Exception;

}
