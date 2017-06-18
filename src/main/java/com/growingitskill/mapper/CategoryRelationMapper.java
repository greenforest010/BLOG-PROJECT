package com.growingitskill.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.PostVO;

public interface CategoryRelationMapper {

	@Insert("INSERT INTO category_relation(post_id, category_id) VALUES(#{id}, #{categoryVO.id})")
	@Results({ @Result(property = "id", column = "post_id"),
			@Result(property = "categoryVO", column = "category_id", javaType = CategoryVO.class, one = @One(select = "selectCategory")) })
	void create(PostVO postVO) throws Exception;

	@Update("INSERT INTO category_relation(post_id, category_id) VALUES(#{id}, #{categoryVO.id}) ON DUPLICATE KEY UPDATE category_id = #{categoryVO.id}")
	void update(PostVO postVO) throws Exception;

}
