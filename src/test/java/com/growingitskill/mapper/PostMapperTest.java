package com.growingitskill.mapper;

import java.util.List;

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
import com.growingitskill.domain.PostVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class PostMapperTest {

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
	private PostMapper postMapper;

	/*@Test
	public void insertPost() throws Exception {
		PostVO postVO = new PostVO();

		postVO.setTitle("타이틀.");
		postVO.setAuthor(1);

		postVO.setContent("내용.");

		// 슬러그 타이틀은 중복될 수 없다. 
		postVO.setSlugTitle("슬러그 타이틀 입니다.6");

		postMapper.create(postVO);

		// postVO를 데이터베이스에 삽입 후 추가 된 행 번호를 id로 가져온다.
		System.out.println("id: " + postVO.getId());
	}*/

	@Test
	public void selectPostById() throws Exception {
		long id = 21;

		PostVO postVO = postMapper.readById(id);

		System.out.println(postVO.toString());
	}
	
	/*@Test
	public void updatePostById() throws Exception {
		PostVO postVO = new PostVO();
		postVO.setId(1);
		postVO.setTitle("It's title");
		postVO.setContent("It's content");
		postVO.setSlugTitle("It's slugTitle5678");
		
		postMapper.update(postVO);
	}*/

	@Test
	public void selectPost() throws Exception {
		List<PostVO> lists = postMapper.listAll();

		for (PostVO postVO : lists) {
			System.out.println(postVO.toString());
		}
	}
	
	/*@Test
	public void deletePost() throws Exception {
		long[] id = {15, 17};
		
		postMapper.deleteByIds(id);
	}*/

}
