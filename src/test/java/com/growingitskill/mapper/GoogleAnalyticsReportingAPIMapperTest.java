package com.growingitskill.mapper;

import static org.junit.Assert.*;

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
import com.growingitskill.domain.GoogleAnalyticsReportingAPIVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MybatisJndiConfig.class)
public class GoogleAnalyticsReportingAPIMapperTest {
	
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
	private GoogleAnalyticsReportingAPIMapper googleAnalyticsReportingAPIMapper;
	
	@Test
	public void googleAnalyticsReportingAPIMapperShouldNotBeNullTest() {
		assertNotNull(googleAnalyticsReportingAPIMapper);
	}
	
	@Test
	public void printTest() throws Exception {
		String apiName = "Service Account";
		
		GoogleAnalyticsReportingAPIVO googleAnalyticsReportingAPIVO = googleAnalyticsReportingAPIMapper.readGoogleAnalyticsReportingAPIByApiName(apiName);
		
		System.out.println(googleAnalyticsReportingAPIVO);
	}

}
