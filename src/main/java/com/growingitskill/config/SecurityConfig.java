package com.growingitskill.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final String USERNAME_QUERY = "SELECT login_id, login_password, enabled FROM member WHERE login_id = ?";
	private final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT login_id, role FROM member WHERE  login_id = ?";

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// POST 요청시, csrf토큰을 보내기 때문에 한글이 깨질수 있으므로, 필터 적용.
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);

		http.addFilterBefore(encodingFilter, CsrfFilter.class);

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/categories/**").permitAll()
				.antMatchers("/categories/**", "/posts/**", "/attachments/**").hasAuthority("ROLE_ADMIN").and()
				.headers().frameOptions().sameOrigin().and().httpBasic();

		http.formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/admin").and().authorizeRequests().antMatchers("/admin/**").authenticated().and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERNAME_QUERY)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY).passwordEncoder(new BCryptPasswordEncoder());
	}
}