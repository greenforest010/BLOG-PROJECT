package com.growingitskill.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.TagVO;
import com.growingitskill.service.TagRelationService;
import com.growingitskill.service.TagService;

@Component
public class TagUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TagUtils.class);

	@Autowired
	private TagService tagService;
	
	@Autowired
	private TagRelationService tagRelationService;

	@Autowired
	private TranslationUtils translationUtils;

	@Autowired
	private SlugUtils slugUtils;

	public void connnectPostAndNewTagList(String tags, PostVO postVO) throws Exception {
		if (isTags(tags)) {
			String[] tagTerms = tags.split(",");

			// 새로운 태그들 생성
			createNewTags(tagTerms);

			// 글-태그 연결
			postVO.setTagList(getTags(tagTerms, postVO));
		}
	}
	
	public void connectPostAndModifiedTagList(String tags, PostVO postVO) throws Exception {
		if (isTags(tags)) {
			String[] tagTerms = tags.split(",");

			// 새로운 태그들 생성
			createNewTags(tagTerms);
			
			// 글에 연계된 모든 태그 삭제
			tagRelationService.removeTagRelationByPostId(postVO.getId());

			// 글-태그 연결
			postVO.setTagList(getTags(tagTerms, postVO));
		}
	}
	
	private boolean isTags(String tags) {
		return (tags != null && !tags.isEmpty());
	}
	
	private void createNewTags(String[] tagTerms) throws Exception {
		List<TagVO> newTagList = new ArrayList<>();
		
		List<String> newTagTermList = getNewTagTermList(tagTerms);

		for (String term : newTagTermList) {
			String slugTerm = slugUtils.slug(translationUtils.translate(term, "ko", "en"));
			
			LOGGER.info("tag slugTerm: " + slugTerm);
			
			
			TagVO tagVO = new TagVO();
			tagVO.setTerm(term);
			tagVO.setSlugTerm(slugTerm);

			newTagList.add(tagVO);
		}

		if (newTagList.size() > 0) {
			tagService.addTags(newTagList);
		}
	}
	
	private List<String> getNewTagTermList(String[] tagTerms) throws Exception {
		List<String> savedTagTermList = tagService.findTagTermByTerms(tagTerms);

		List<String> newTagTermList = new ArrayList<>();
		Collections.addAll(newTagTermList, tagTerms);

		for (String term : savedTagTermList) {
			newTagTermList.remove(term);
		}
		
		return newTagTermList;
	}

	private List<TagVO> getTags(String[] tagTerms, PostVO postVO) throws Exception {
		List<TagVO> tagList = new ArrayList<>();
		
		List<Long> tagIdList = tagService.findTagIdByTerms(tagTerms);

		for (Long id : tagIdList) {
			TagVO tagVO = new TagVO();
			tagVO.setId(id);

			tagList.add(tagVO);
		}
		
		return tagList;
	}

}
