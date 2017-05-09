package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
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

	@Delete({"<script>",
		"DELETE FROM post WHERE id IN",
		"<foreach item='item' index='index' collection='postId' open='(' separator=',' close=')'>",
		"#{item}",
		"</foreach>",
		"</script>"})
	void delete(@Param("postId") long[] postId) throws Exception;

	@Select("SELECT * FROM post WHERE id > 0 order by id desc")
	@Results(@Result(property="slugTitle", column="slug_title")) // DB 컬럼과 JAVA 필드의 이름이 다른 경우 사용
	List<PostVO> listAll() throws Exception;

}
