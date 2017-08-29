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
	
	@Autowired TagRelationMapper tagRelationMapper;

	@Transactional
	@Override
	public void regist(PostVO postVO) throws Exception {
		postMapper.create(postVO);
		
		categoryRelationMapper.create(postVO);
		
		if (postVO.getTagList() != null && postVO.getTagList().size() > 0) {
			tagRelationMapper.create(postVO);
		}
	}

	@Override
	public PostVO findById(long id) throws Exception {
		return postMapper.readById(id);
	}

	@Transactional
	@Override
	public void modify(PostVO postVO) throws Exception {
		postMapper.update(postVO);
		
		categoryRelationMapper.update(postVO);
		
		if (postVO.getTagList() != null && postVO.getTagList().size() > 0) {
			tagRelationMapper.create(postVO);
		}
	}

	@Override
	public void removeByIds(long[] ids) throws Exception {
		postMapper.deleteByIds(ids);
	}

	@Override
	public List<PostVO> listAll() throws Exception {
		return postMapper.listAll();
	}
	
	@Override
	public List<PostVO> findList(SearchCriteria searchCriteria) throws Exception {
		return postMapper.readList(searchCriteria);
	}
	
	@Override
	public int countCriteria(SearchCriteria searchCriteria) throws Exception {
		return postMapper.countPaging(searchCriteria);
	}

	@Override
	public List<PostVO> findListByCategory(Set<Long> categoryLevelSet, SearchCriteria searchCriteria) throws Exception {
		return postMapper.readListByCategory(categoryLevelSet, searchCriteria);
	}

	@Override
	public int countCriteriaByCategory(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		return postMapper.countPagingByCategory(slugTerm, searchCriteria);
	}

	@Override
	public List<PostVO> findListByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		return postMapper.readListByTag(slugTerm, searchCriteria);
	}

	@Override
	public int countCriteriaByTag(String slugTerm, SearchCriteria searchCriteria) throws Exception {
		return postMapper.countPagingByTag(slugTerm, searchCriteria);
	}

}
