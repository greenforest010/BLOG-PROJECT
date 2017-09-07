package com.growingitskill.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class PostMapperTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostMapperTest.class);

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
	
	@Autowired
	private CategoryMapper categoryMapper;

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

	/*@Test
	public void selectPostById() throws Exception {
		long id = 21;

		PostVO postVO = postMapper.readById(id);

		System.out.println(postVO.toString());
	}*/
	
	@Test
	public void findPostBySlugTitle() throws Exception {
		String slugTitle = "울랄라";

		PostVO postVO = postMapper.readPostBySlugTitle(slugTitle);

		LOGGER.info(postVO.toString());
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

	/*@Test
	public void selectPost() throws Exception {
		List<PostVO> lists = postMapper.listAll();

		for (PostVO postVO : lists) {
			System.out.println("original: " + postVO.toString());
		}
	}*/
	
	/*@Test
	public void deletePost() throws Exception {
		long[] id = {15, 17};
		
		postMapper.deleteByIds(id);
	}*/
	
	/*@Test
	public void selectPostListPage() throws Exception {
		Criteria criteria = new Criteria();
		criteria.setPage(2);
		criteria.setPerPageNum(20);
		
		List<PostVO> list = postMapper.readList(criteria);
		
		for (PostVO postVO : list) {
			System.out.println(postVO.toString());
		}
	}*/
	
	/*@Test
	public void testDynamic1() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPage(1);
		searchCriteria.setKeyword("캬");
		
		List<PostVO> list = postMapper.readList(searchCriteria);
		
		for (PostVO postVO : list) {
			System.out.println(postVO.getId() + ": " + postVO.getTitle());
		}
		
		System.out.println("COUNT: " + postMapper.countPaging(searchCriteria));
	}*/
	
	/*@Test
	public void testDynamic2() throws Exception {
		String slugTerm = "전체";
		Set<Long> categoryLevelSet = makeCategoryLevelSet(slugTerm);
		
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPage(1);
		searchCriteria.setKeyword("");
		
		List<PostVO> list = postMapper.readListByCategory(categoryLevelSet, searchCriteria);
		
		for (PostVO postVO : list) {
			System.out.println(postVO.toString());
		}
		
		System.out.println("COUNT: " + postMapper.countPagingByCategory(slugTerm, searchCriteria));
	}*/
	
	/*private Set<Long> makeCategoryLevelSet(String slugTerm) throws Exception {
		List<CategoryLevel> listCategoryLevel = categoryMapper.listCategoryLevel(slugTerm);
		
		Set<Long> set = new HashSet<>();
		
		for (CategoryLevel categoryLevel : listCategoryLevel) {
			set.add(categoryLevel.getLevel1());
			set.add(categoryLevel.getLevel2());
			set.add(categoryLevel.getLevel3());
			set.add(categoryLevel.getLevel4());
		}
		
		if (set.contains((long) 0)) {
			set.remove((long) 0);
		}
		
		return set;
	}*/
	
	/*@Test
	public void findListByTag() throws Exception {
		String slugTerm = "옐로우";
		
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setPage(1);
		searchCriteria.setKeyword("");
		
		List<PostVO> list = postMapper.readListByTag(slugTerm, searchCriteria);
		
		for (PostVO postVO : list) {
			System.out.println(postVO.toString());
		}
		
		System.out.println("COUNT: " + postMapper.countPagingByTag(slugTerm, searchCriteria));
	}*/

}
