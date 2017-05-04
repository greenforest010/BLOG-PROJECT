package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.PostVO;

public interface PostMapper {

	@Insert("INSERT INTO post(title, author, content, slug_title) VALUES(#{title}, #{author}, #{content}, #{slugTitle})")
	void create(PostVO vo) throws Exception;

	@Select("SELECT * FROM post WHERE id = #{id}")
	PostVO read(@Param("id") long id) throws Exception;

	@Update("UPDATE post SET title = #{title}, content = #{content} where id = #{id}")
	void update(PostVO vo) throws Exception;

	@Delete("DELETE FROM post WHERE id = #{id}")
	void delete(@Param("id") long id) throws Exception;

	
	List<PostVO> listAll() throws Exception;

}
