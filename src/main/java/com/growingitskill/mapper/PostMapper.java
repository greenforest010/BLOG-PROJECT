package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.PostVO;

public interface PostMapper {

	@Insert("INSERT INTO post(title, author, content, slug_title) VALUES(#{title}, #{author}, #{content}, #{slugTitle})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void create(PostVO vo) throws Exception;

	@Select("SELECT title, login_id, content, published FROM post p LEFT JOIN member m ON p.author = m.id WHERE p.id = #{id} ORDER BY p.id DESC")
	@Results(@Result(property = "loginId", column = "login_id"))
	PostVO read(long id) throws Exception;

	@Update("UPDATE post SET title = #{title}, content = #{content} where id = #{id}")
	void update(PostVO vo) throws Exception;

	@Delete({ "<script>", "DELETE FROM post WHERE id IN",
			"<foreach item='item' index='index' collection='array' open='(' separator=',' close=')'>", "#{item}",
			"</foreach>", "</script>" })
	void delete(long[] postId) throws Exception;

	/*
	 * Join 한 테이블의 association에 해당하는 @ONE annotation을 쓴 결과가 제대로 나오지 않음.
	 * -> xml mapper로 대체
	 */
	List<PostVO> listAll() throws Exception;

}
