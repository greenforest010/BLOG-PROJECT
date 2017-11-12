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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MybatisJndiConfig.class)
public class MemberMapperTest {
	
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
	private MemberMapper memberMapper;
	
	/*@Test
	public void getIdByLoginId() throws Exception {
		String loginId = "testuser";
		
		System.out.println("id: " + memberMapper.readIdByLoginId(loginId));
	}*/
	
	/*@Test
	public void getMemberById() throws Exception {
		long id = 1;
		
		MemberVO memberVO = memberMapper.readMemberById(id);
		
		System.out.println(memberVO.toString());
	}*/
	
	@Test
	public void getMemberByAdminRole() throws Exception {
		MemberVO memberVO = memberMapper.readMemberByAdminRole();
		
		System.out.println(memberVO.toString());
	}
	
	/*@Test
	public void getMemberByLoginId() throws Exception {
		String loginId = "testuser";
		
		System.out.println(memberMapper.readMemberByLoginId(loginId).toString());
	}*/
	
	/*@Test
	public void updateMember() throws Exception {
		String loginId = "testuser";
		
		MemberVO memberVO = new MemberVO();
		memberVO.setDisplayName(loginId);
		memberVO.setLoginId(loginId);
		memberVO.setNickname(loginId);
		
		memberMapper.updateMemberByLoginId(memberVO);
		
		System.out.println(memberMapper.readMemberByLoginId(loginId).toString());
	}*/
}
