package com.growingitskill.mapper;

import java.util.ArrayList;
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
import com.growingitskill.domain.TagVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class TagMapperTest {

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
	private TagMapper tagMapper;

	@Autowired
	private TagRelationMapper tagRelationMapper;

	/*
	 * @Test public void findList() throws Exception { List<TagVO> list =
	 * tagMapper.listAll();
	 * 
	 * for (TagVO tagVO : list) { System.out.println(tagVO); } }
	 */

	/*
	 * @Test public void registTag() throws Exception { TagVO tagVO = new
	 * TagVO(); tagVO.setTerm("은룡"); tagVO.setSlugTerm("은룡");
	 * 
	 * tagMapper.createTag(tagVO); }
	 */

	/*
	 * @Test public void findTagById() throws Exception { long id = 100;
	 * 
	 * System.out.println(tagMapper.readTagById(id)); }
	 */

	/*
	 * @Test public void modifyTermById() throws Exception { long id = 109;
	 * String term = "부산";
	 * 
	 * tagMapper.updateTermById(id, term); }
	 */

	/*
	 * @Test public void modifySlugTermById() throws Exception { long id = 100;
	 * String slugTerm = "부산";
	 * 
	 * tagMapper.updateSlugTermById(id, slugTerm); }
	 */

	/*
	 * @Test public void removeTagById() throws Exception { long id = 109;
	 * 
	 * tagMapper.deleteTagById(id); }
	 */

	/*
	 * @Test public void findTagsByTerms() throws Exception { String[] terms =
	 * {"항구", "루", "검은룡", "은룡", "자"};
	 * 
	 * List<String> list = tagMapper.readTagTermByTerms(terms);
	 * 
	 * for (String term : list) { System.out.println(term); } }
	 */

	/*
	 * @Test public void addTags() throws Exception { List<TagVO> tags = new
	 * ArrayList<>();
	 * 
	 * for (int i = 50; i < 100; i += 5) { TagVO tagVO = new TagVO();
	 * tagVO.setTerm("태그" + i); tagVO.setSlugTerm("태그" + i);
	 * 
	 * tags.add(tagVO); }
	 * 
	 * tagMapper.createTags(tags); }
	 */

	/*
	 * @Test public void findTagIdByTerms() throws Exception { String[] terms =
	 * {"항구", "루", "검은룡", "은룡", "자"};
	 * 
	 * List<Long> tags = tagMapper.readTagIdByTerms(terms);
	 * 
	 * for (Long id : tags) { System.out.println(id); } }
	 */

	/*
	 * @Test public void findTagIdByTerms() throws Exception { long postId = 6;
	 * 
	 * List<String> terms = tagMapper.readTagTermByPostId(postId);
	 * 
	 * for (String string : terms) { System.out.println(string); } }
	 */

	@Test
	public void findTagByPostId() throws Exception {
		long postId = 3;

		List<TagVO> tags = tagMapper.readTagByPostId(postId);

		for (TagVO tagVO : tags) {
			System.out.println(tagVO);
		}
	}

}
