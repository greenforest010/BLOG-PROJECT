package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.mapper.AboutMapper;

@Service
public class AboutServiceImpl implements AboutService {
	
	@Autowired
	private AboutMapper aboutMapper;

	@Override
	public String findAbout() throws Exception {
		return aboutMapper.readAbout();
	}

	@Override
	public void modifyAboutContent(String content) throws Exception {
		aboutMapper.updateAboutContent(content);
	}
	
	

}
