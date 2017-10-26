package com.growingitskill.mapper;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.BlogInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class BlogInfoMapperTest {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(BlogInfoMapperTest.class);
	
	@Autowired
	private BlogInfoMapper blogInfoMapper;

	@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}
	
	@Test
	public void testReadBlogInfo() throws Exception {
		BlogInfo blogInfo = blogInfoMapper.readBlogInfo();
		
		LOGGER.info(blogInfo.toString());
	}
	
	@Test
	public void testUpdateBlogInfo() throws Exception {
		BlogInfo blogInfo = new BlogInfo();
		blogInfo.setTitle("호호호");
		blogInfo.setSubtitle("하하");
		
		blogInfoMapper.updateBlogInfo(blogInfo);
	}

}
