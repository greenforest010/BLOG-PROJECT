package com.growingitskill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.mapper.AboutMapper;

@Service
public class AboutServiceImpl implements AboutService {
	
	@Autowired
	private AboutMapper aboutMapper;

	@Override
	public String find() throws Exception {
		return aboutMapper.read();
	}

	@Override
	public void modify(String content) throws Exception {
		aboutMapper.update(content);
	}
	
	

}
