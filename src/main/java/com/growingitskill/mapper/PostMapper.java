package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.PostVO;
import com.growingitskill.sqlprovider.PostSqlProvider;

public interface PostMapper {

	@Insert("INSERT INTO post(title, author, content, slug_title) VALUES(#{title}, #{author}, #{content}, #{slugTitle})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void create(PostVO postVO) throws Exception;

	/*
	 * xml mapper로 대체
	 */
	PostVO readById(long id) throws Exception;

	@Update("UPDATE post SET title = #{title}, content = #{content}, slug_title = #{slugTitle} where id = #{id}")
	void update(PostVO postVO) throws Exception;

	@DeleteProvider(type=PostSqlProvider.class, method="deleteByIds")
	void deleteByIds(@Param("ids") long[] ids) throws Exception;

	/*
	 * Join 한 테이블의 association에 해당하는 @ONE annotation을 쓴 결과가 제대로 나오지 않음. -> xml
	 * mapper로 대체
	 */
	List<PostVO> listAll() throws Exception;

}
