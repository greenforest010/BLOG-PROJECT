package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.TagVO;

public interface TagMapper {

	@Select("SELECT * FROM tag ORDER BY id DESC")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	List<TagVO> readTags() throws Exception;

	@Insert("INSERT INTO tag(term, slug_term) VALUES(#{term}, #{slugTerm})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void createTag(TagVO tagVO) throws Exception;

	/*
	 * xml로 대체
	 */
	void createTags(@Param("tags") List<TagVO> tags) throws Exception;

	@Select("SELECT * FROM tag WHERE id = #{id}")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	TagVO readTagById(long id) throws Exception;

	@Select("SELECT * FROM tag WHERE slug_term LIKE #{slugTerm}")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	TagVO readTagBySlugTerm(String slugTerm) throws Exception;

	/*
	 * xml로 대체
	 */
	List<TagVO> readTagByPostId(long postId) throws Exception;

	/*
	 * xml로 대체
	 */
	List<String> readTagTermByPostId(long postId) throws Exception;

	/*
	 * xml로 대체
	 */
	List<Long> readTagIdByTerms(@Param("terms") String[] terms) throws Exception;

	/*
	 * xml로 대체
	 */
	List<String> readTagTermByTerms(@Param("terms") String[] terms) throws Exception;

	@Update("UPDATE tag SET term = #{term}, slug_term = #{slugTerm} WHERE id = #{id}")
	void updateTagTermAndSlugTermById(@Param("id") long id, @Param("term") String term, @Param("slugTerm") String slugTerm)
			throws Exception;

	@Update("UPDATE tag SET slug_term = #{slugTerm} WHERE id = #{id}")
	void updateTagSlugTermById(@Param("id") long id, @Param("slugTerm") String slugTerm) throws Exception;

	@Delete("DELETE FROM tag WHERE id = #{id}")
	void deleteTagById(long id) throws Exception;

}
