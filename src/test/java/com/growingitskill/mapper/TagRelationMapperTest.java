package com.growingitskill.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.TagVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class TagRelationMapperTest {
	
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
	private TagRelationMapper tagRelationMapper;
	
	/*@Test
	public void findTagsByPostId() throws Exception {
		long id = 3;
		
		List<Long> tags = tagRelationMapper.readTagsByPostId(id);
		
		for (Long tagId : tags) {
			System.out.println(tagId);
		}
	}*/
	
	/*@Test
	public void regist() throws Exception {
		long id1 = 105;
		long id2 = 106;
		long id3 = 107;
		
		TagVO tagVO1 = new TagVO();
		tagVO1.setId(id1);
		
		TagVO tagVO2 = new TagVO();
		tagVO2.setId(id2);
		
		TagVO tagVO3 = new TagVO();
		tagVO3.setId(id3);
		
		List<TagVO> tags = new ArrayList<>();
		tags.add(tagVO1);
		tags.add(tagVO2);
		tags.add(tagVO3);
		
		PostVO postVO = new PostVO();
		postVO.setId(5);
		postVO.setTagList(tags);
		
		System.out.println(postVO.toString());
		
		tagRelationMapper.create(postVO);
	}*/
	
	@Test
	public void remove() throws Exception {
		long postId = 2;
		
		tagRelationMapper.deleteByPostId(postId);
	}

}
