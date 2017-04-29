package com.growingitskill.database;

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
import com.growingitskill.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MybatisJndiConfig.class)
public class UserDAOTest {
	
	/*@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/test");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}*/
	
	@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		dataSource.setUrl("jdbc:log4jdbc:mysql://localhost/test");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testGetUser() throws Exception {
		userMapper.getUser("wow");
	}
	
	@Test
	public void testSetUser() throws Exception {
		userMapper.setUser("haha");
	}

}
