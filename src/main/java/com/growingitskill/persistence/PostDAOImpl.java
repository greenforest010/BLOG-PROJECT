package com.growingitskill.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.mapper.PostMapper;

public class PostDAOImpl extends SqlSessionDaoSupport implements PostMapper {

	private static String namespace = "com.growingitskill.mapper.PostMapper";

	@Override
	public void createPost(PostVO postVO) throws Exception {
		getSqlSession().insert(namespace + ".create", postVO);
	}

	@Override
	public PostVO readPostById(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readPostById", id);
	}

	@Override
	public PostVO readPostBySlugTitle(String slugTitle) throws Exception {
		return getSqlSession().selectOne(namespace + ".readPostBySlugTitle", slugTitle);
	}

	@Override
	public void updatePost(PostVO postVO) throws Exception {
		getSqlSession().update(namespace + ".updatePost", postVO);
	}

	@Override
	public void deletePostByIds(long[] ids) throws Exception {
		getSqlSession().delete(namespace + ".deletePostByIds", ids);
	}

	@Override
	public List<PostVO> readPosts() throws Exception {
		return getSqlSession().selectList(namespace + ".readPosts");
	}

	@Override
	public List<PostVO> readPostsWithCriteria(SearchCriteria searchCriteria) throws Exception {
		return getSqlSession().selectList(namespace + ".readPostsWithCriteria", searchCriteria);
	}

	@Override
	public int countPostWithCriteriaPaging(SearchCriteria searchCriteria) throws Exception {
		return getSqlSession().selectOne(namespace + ".countPostWithCriteriaPaging", searchCriteria);
	}

	@Override
	public List<PostVO> readPostsWithCriteriaByCategory(Set<Long> categoryLevelSet, SearchCriteria searchCriteria)
			throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("categoryLevelSet", categoryLevelSet);
		map.put("criteria", searchCriteria);

		return getSqlSession().selectList(namespace + ".readPostsWithCriteriaByCategory", map);
	}

	@Override
	public int countPostPagingByCategory(Set<Long> categoryLevelSet) throws Exception {

		return getSqlSession().selectOne(namespace + ".countPostPagingByCategory", categoryLevelSet);
	}

	@Override
	public List<PostVO> readPostsWithCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("slugTerm", slugTerm);
		map.put("criteria", searchCriteria);

		return getSqlSession().selectList(namespace + ".readPostsWithCriteriaByTag", map);
	}

	@Override
	public int countPostWithCriteriaPagingByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("slugTerm", slugTerm);
		map.put("criteria", searchCriteria);

		return getSqlSession().selectOne(namespace + ".countPostWithCriteriaPaginByTag", map);
	}

}
