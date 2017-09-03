package com.growingitskill.mapper;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class AboutMapperTest {

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
	
	@Autowired
	private AboutMapper aboutMapper;
	
	@Test
	public void find() throws Exception {
		System.out.println(aboutMapper.read());
	}
	
	@Test
	public void modify() throws Exception {
		String content = "배드 가이즈";
		
		aboutMapper.update(content);
	}

}
