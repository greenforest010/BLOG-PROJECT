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
	List<TagVO> listAll() throws Exception;

	@Insert("INSERT INTO tag(term, slug_term) VALUES(#{term}, #{slugTerm})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void createTag(TagVO tagVO) throws Exception;

	@Select("SELECT * FROM tag WHERE id = #{id}")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	TagVO readTagById(long id) throws Exception;

	@Update("UPDATE tag SET term = #{term} WHERE id = #{id}")
	void updateTermById(@Param("id") long id, @Param("term") String term) throws Exception;

	@Update("UPDATE tag SET slug_term = #{slugTerm} WHERE id = #{id}")
	void updateSlugTermById(@Param("id") long id, @Param("slugTerm") String slugTerm) throws Exception;

	@Delete("DELETE FROM tag WHERE id = #{id}")
	void deleteTagById(long id) throws Exception;

}
