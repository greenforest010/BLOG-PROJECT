package com.growingitskill.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class MybatisJndiConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public JndiObjectFactoryBean dataSource() {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/test");
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
