package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.growingitskill.domain.PostVO;

public interface TagRelationMapper {

	@Select("SELECT tag_id FROM tag_relation WHERE post_id = #{id}")
	List<Long> readTagsByPostId(long id) throws Exception;

	/*
	 * xml로 대체
	 */
	void createTagRelation(PostVO postVO) throws Exception;

	@Delete("DELETE FROM tag_relation WHERE post_id = #{postId}")
	void deleteTagRelationByPostId(long postId) throws Exception;

}
