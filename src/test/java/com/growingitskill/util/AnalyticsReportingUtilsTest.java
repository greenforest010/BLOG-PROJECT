package com.growingitskill.util;

import static org.junit.Assert.assertNotNull;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.growingitskill.config.MybatisJndiConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class AnalyticsReportingUtilsTest {

	@Autowired
	private AnalyticsReportingUtils analyticsReportingUtils;

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

	@Test
	public void analyticsReportingUtilsShouldNotBeNullTest() {
		assertNotNull(analyticsReportingUtils);
	}
	
	@Test
	public void apiTest() throws Exception {
		AnalyticsReporting service = analyticsReportingUtils.initializeAnalyticsReporting();
		GetReportsResponse response = analyticsReportingUtils.getReport(service);
		
		analyticsReportingUtils.printResponse(response);
	}
	
	@Test
	public void getVisitorCount() throws Exception {
		AnalyticsReporting service = analyticsReportingUtils.initializeAnalyticsReporting();
		GetReportsResponse response = analyticsReportingUtils.getReport(service);
		
		String visitorCount = analyticsReportingUtils.getNewVisitors(response);
		
		System.out.println("Visitor Count: " + visitorCount);
	}

}
