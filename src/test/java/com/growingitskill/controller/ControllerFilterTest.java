package com.growingitskill.controller;

import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.filter.CharacterEncodingFilter;

public class ControllerFilterTest {
	
	@Test
	public void testAddFilter() throws Exception {
		IndexController controller = new IndexController();
		MockMvc mockMvc = standaloneSetup(controller).addFilter(new CharacterEncodingFilter("UTF-8")).build();
		
		mockMvc.perform(get("/")).andExpect(view().name("index"));
	}

}
