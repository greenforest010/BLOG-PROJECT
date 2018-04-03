package com.growingitskill.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
@ComponentScan({"com.growingitskill.service", "com.growingitskill.util"})
//MapperScan은 매퍼를 검색하고 자동 빈을 만들어 주기 때문에 매퍼를 구현한 DAOImpl는 Repository로 빈을 등록할 필요가
//없다
@MapperScan("com.growingitskill.mapper")
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
		sqlSessionFB.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));

		return sqlSessionFB.getObject();
	}
}
