package com.growingitskill.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * 이 클래스는 "root-context.xml" 역할을 대체한다.
 */

@Configuration
@ComponentScan(basePackages = { "com.growingitskill" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public JndiObjectFactoryBean dataSource() {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/blop");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(DataSource.class);

		return jndiObjectFB;
	}

	@Bean
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFB = new SqlSessionFactoryBean();
		sqlSessionFB.setDataSource((DataSource) dataSource().getObject());
		sqlSessionFB.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));

		return sqlSessionFB.getObject();
	}

}
