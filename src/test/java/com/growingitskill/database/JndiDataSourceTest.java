package com.growingitskill.database;

import java.sql.Connection;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.JndiConfig;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit4ClassRunner는 WAS 구동 하지 않음.
@ContextConfiguration(classes=JndiConfig.class)
public class JndiDataSourceTest {
	
	/*
	 * 출처: http://toby.epril.com/?p=427, http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/mock/jndi/SimpleNamingContextBuilder.html
	 */
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
	private DataSource dataSource;
	
	@Test
	public void testConnection() throws Exception {
		try(Connection connection = dataSource.getConnection()) {
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
