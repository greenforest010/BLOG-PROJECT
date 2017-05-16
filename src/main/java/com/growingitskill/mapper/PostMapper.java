package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.PostVO;

public interface PostMapper {

	@Insert("INSERT INTO post(title, author, content, slug_title) VALUES(#{title}, #{author}, #{content}, #{slugTitle})")
	void create(PostVO vo) throws Exception;

	@Select("SELECT title, login_id, content, published FROM post p LEFT JOIN member m ON p.author = m.id WHERE p.id = #{id} ORDER BY p.id DESC")
	@Results(@Result(property="loginId", column="login_id"))
	PostVO read(long id) throws Exception;

	@Update("UPDATE post SET title = #{title}, content = #{content} where id = #{id}")
	void update(PostVO vo) throws Exception;

	@Delete({"<script>",
		"DELETE FROM post WHERE id IN",
		"<foreach item='item' index='index' collection='array' open='(' separator=',' close=')'>",
		"#{item}",
		"</foreach>",
		"</script>"})
	void delete(long[] postId) throws Exception;

	@Select("SELECT p.id, title, login_id, published, slug_title FROM post p LEFT JOIN member m ON p.author = m.id ORDER BY p.id DESC")
	@Results({@Result(property="loginId", column="login_id"), @Result(property="slugTitle", column="slug_title")}) // DB 컬럼과 JAVA 필드의 이름이 다른 경우 사용
	List<PostVO> listAll() throws Exception;

}
