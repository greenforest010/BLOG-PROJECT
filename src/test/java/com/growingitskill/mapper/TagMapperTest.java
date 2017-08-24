package com.growingitskill.mapper;

import java.util.List;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.TagVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class TagMapperTest {
	
	@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/test");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}
	
	@Autowired
	private TagMapper tagMapper;
	
	@Test
	public void findList() throws Exception {
		List<TagVO> list = tagMapper.listAll();
		
		for (TagVO tagVO : list) {
			System.out.println(tagVO);
		}
	}
	
	/*@Test
	public void registTag() throws Exception {
		TagVO tagVO = new TagVO();
		tagVO.setTerm("은룡");
		tagVO.setSlugTerm("은룡");
		
		tagMapper.createTag(tagVO);
	}*/
	
	/*@Test
	public void findTagById() throws Exception {
		long id = 100;
		
		System.out.println(tagMapper.readTagById(id));
	}*/
	
	/*@Test
	public void modifyTermById() throws Exception {
		long id = 109;
		String term = "부산";
		
		tagMapper.updateTermById(id, term);
	}*/
	
	/*@Test
	public void modifySlugTermById() throws Exception {
		long id = 100;
		String slugTerm = "부산";
		
		tagMapper.updateSlugTermById(id, slugTerm);
	}*/
	
	/*@Test
	public void removeTagById() throws Exception {
		long id = 109;
		
		tagMapper.deleteTagById(id);
	}*/

}
