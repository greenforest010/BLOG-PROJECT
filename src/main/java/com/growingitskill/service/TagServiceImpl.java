package com.growingitskill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.TagVO;
import com.growingitskill.mapper.TagMapper;

@Service
public class TagServiceImpl implements TagService{
	
	@Autowired
	private TagMapper tagMapper;

	@Override
	public List<TagVO> findTagList() throws Exception {
		return tagMapper.listAll();
	}

	@Override
	public void addTag(TagVO tagVO) throws Exception {
		tagMapper.createTag(tagVO);
	}
	
	@Override
	public void addTags(List<TagVO> tags) throws Exception {
		tagMapper.createTags(tags);
	}

	@Override
	public TagVO findTagById(long id) throws Exception {
		return tagMapper.readTagById(id);
	}
	
	@Override
	public List<TagVO> findTagByPostId(long postId) throws Exception {
		return tagMapper.readTagByPostId(postId);
	}
	
	@Override
	public List<String> findTagTermByPostId(long postId) throws Exception {
		return tagMapper.readTagTermByPostId(postId);
	}
	
	@Override
	public List<Long> findTagIdByTerms(String[] terms) throws Exception {
		return tagMapper.readTagIdByTerms(terms);
	}
	
	@Override
	public List<String> findTagTermByTerms(String[] terms) throws Exception {
		return tagMapper.readTagTermByTerms(terms);
	}

	@Override
	public void modifyTermById(long id, String term) throws Exception {
		tagMapper.updateTermById(id, term);
	}

	@Override
	public void modifySlugTermById(long id, String slugTerm) throws Exception {
		tagMapper.updateSlugTermById(id, slugTerm);
	}

	@Override
	public void removeTagById(long id) throws Exception {
		tagMapper.deleteTagById(id);
	}

}