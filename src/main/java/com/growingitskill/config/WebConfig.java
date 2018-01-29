package com.growingitskill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/*
 * 이 클래스는 "servlet-context.xml" 역할을 대체한다.
 */

@Configuration
@EnableWebMvc
@ComponentScan({"com.growingitskill.controller", "com.growingitskill.feed"})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/layout/tiles.xml"});
		tilesConfigurer.setCheckRefresh(true);
		
		return tilesConfigurer;
	}
	
	@Bean
	public ViewResolver beanNameViewResolver() {
		return new BeanNameViewResolver();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		return new TilesViewResolver();
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws Exception {
		return new StandardServletMultipartResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}*/
}
