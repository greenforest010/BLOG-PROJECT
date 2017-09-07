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
import com.growingitskill.domain.CategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class CategoryMapperTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryMapperTest.class);

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
	private CategoryMapper categoryMapper;

	/*@Test
	public void selectCategoryById() throws Exception {
		long id = 2;

		CategoryVO categoryVO = categoryMapper.readCategoryById(id);

		logger.info(categoryVO.toString());
	}*/
	
	@Test
	public void findCategoryBySlugTerm() throws Exception {
		String slugTerm = "전체";
		
		CategoryVO categoryVO = categoryMapper.readCategoryBySlugTerm(slugTerm);
		
		LOGGER.info(categoryVO.toString());
	}

	/*@Test
	public void selectCategory() throws Exception {
		List<CategoryVO> lists = categoryMapper.listAll();

		for (CategoryVO categoryVO : lists) {
			logger.info(categoryVO.toString());
		}
	}*/
	
	/*@Test
	public void insertCategory() throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setTerm("카테고리다");
		categoryVO.setSlugTerm("slug다");
		categoryVO.setParent(2);
		
		categoryMapper.create(categoryVO);
		
		logger.info("id: " + categoryVO.getId());
	}*/
	
	/*@Test
	public void updateTerm() throws Exception {
		long id = 8;
		String term = "마자용";
		String slugTerm = term;
		
		categoryMapper.updateTermAndSlugTermById(id, term, slugTerm);
	}*/
	
	/*@Test
	public void updateSlugTerm() throws Exception {
		long id = 5;
		String slugTerm = "fnfnfn";
		
		categoryMapper.updateSlugTermById(id, slugTerm);
	}*/
	
	/*@Test
	public void updateParent() throws Exception {
		long id = 5;
		long parent = 4;
		
		categoryMapper.updateParentById(id, parent);
	}*/
	
	/*@Test
	public void deleteCategory() throws Exception {
		long id = 13;
		
		categoryMapper.deleteCategoryById(id);
	}*/
	
	/*@Test
	public void listLeafCategory() throws Exception {
		System.out.println(categoryMapper.listLeafCategory());
	}*/
	
	/*@Test
	public void listCategoryLevel() throws Exception {
		String slugTerm = "전체";
		
		List<CategoryLevel> list = categoryMapper.listCategoryLevel(slugTerm);
		Set<Long> set = new HashSet<>();
		
		for (CategoryLevel categoryLevel : list) {
			set.add(categoryLevel.getLevel1());
			set.add(categoryLevel.getLevel2());
			set.add(categoryLevel.getLevel3());
			set.add(categoryLevel.getLevel4());
			
			System.out.println(categoryLevel);
		}
		
		if (set.contains((long) 0)) {
			set.remove((long) 0);
		}
		
		System.out.println(set);
	}*/

}
