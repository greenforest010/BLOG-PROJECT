package com.growingitskill.controller;

import  static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class ControllerTest {

		// index 페이지 요청 시 뷰 이름이 "index"인지 테스트
		@Test
		public void testIndexPage() throws Exception {
			IndexController controller = new IndexController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/")).andExpect(view().name("index")); // get "/" 요청 수행
		}
		
		// about 페이지 요청 시 뷰 이름이 "about"인지 테스트(실패)
		/*@Test
		public void testAboutPage() throws Exception {
			IndexController controller = new IndexController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/about")).andExpect(view().name("about")); // url과 name이 같은 경우 테스트 실패
		}*/
		
		@Test
		public void testAdminPage() throws Exception {
			AdminController controller = new AdminController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/admin")).andExpect(view().name("admin/main"));
		}
		
		/*@Test
		public void testLoginPage() throws Exception {
			IndexController controller = new IndexController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/login")).andExpect(view().name("admin/login"));
		}*/
		
		@Test
		public void testPostListPage() throws Exception {
			AdminController controller = new AdminController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/admin/post-list")).andExpect(view().name("admin/postList"));
		}
		
		@Test
		public void testCategory() throws Exception {
			AdminController controller = new AdminController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/admin/category")).andExpect(view().name("admin/category"));
		}
		
		@Test
		public void testTag() throws Exception {
			AdminController controller = new AdminController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/admin/tag")).andExpect(view().name("admin/tag"));
		}
		
		@Test
		public void testMedia() throws Exception {
			AdminController controller = new AdminController();
			MockMvc mockMvc = standaloneSetup(controller).build();
			
			mockMvc.perform(get("/admin/media")).andExpect(view().name("admin/media"));
		}
}