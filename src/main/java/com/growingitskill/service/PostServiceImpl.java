package com.growingitskill.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.mapper.CategoryRelationMapper;
import com.growingitskill.mapper.PostMapper;
import com.growingitskill.mapper.TagRelationMapper;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private CategoryRelationMapper categoryRelationMapper;

	@Autowired
	TagRelationMapper tagRelationMapper;

	@Transactional
	@Override
	public void registPost(PostVO postVO) throws Exception {
		postMapper.createPost(postVO);

		categoryRelationMapper.createCategoryRelation(postVO);

		if (postVO.getTagList() != null && postVO.getTagList().size() > 0) {
			tagRelationMapper.createTagRelation(postVO);
		}
	}

	@Override
	public PostVO findPostById(long id) throws Exception {
		return postMapper.readPostById(id);
	}

	@Override
	public PostVO findPostBySlugTitle(String slugTitle) throws Exception {
		return postMapper.readPostBySlugTitle(slugTitle);
	}

	@Transactional
	@Override
	public void modifyPost(PostVO postVO) throws Exception {
		postMapper.updatePost(postVO);

		categoryRelationMapper.updateCategoryRelation(postVO);

		if (postVO.getTagList() != null && postVO.getTagList().size() > 0) {
			tagRelationMapper.createTagRelation(postVO);
		}
	}

	@Override
	public void removePostByIds(long[] ids) throws Exception {
		postMapper.deletePostByIds(ids);
	}

	@Override
	public List<PostVO> findPosts() throws Exception {
		return postMapper.readPosts();
	}

	@Override
	public List<PostVO> findPostsWithCriteria(SearchCriteria searchCriteria) throws Exception {
		return postMapper.readPostsWithCriteria(searchCriteria);
	}

	@Override
	public int countPostWithCriteria(SearchCriteria searchCriteria) throws Exception {
		return postMapper.countPostWithCriteriaPaging(searchCriteria);
	}

	@Override
	public List<PostVO> findPostsWithCriteriaByCategory(Set<Long> categoryLevelSet, SearchCriteria searchCriteria)
			throws Exception {
		return postMapper.readPostsWithCriteriaByCategory(categoryLevelSet, searchCriteria);
	}

	@Override
	public int countPostByCategory(Set<Long> categoryLevelSet) throws Exception {
		return postMapper.countPostPagingByCategory(categoryLevelSet);
	}

	@Override
	public List<PostVO> findPostsWithCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		return postMapper.readPostsWithCriteriaByTag(slugTerm, searchCriteria);
	}

	@Override
	public int countPostWithCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		return postMapper.countPostWithCriteriaPagingByTag(slugTerm, searchCriteria);
	}

}
