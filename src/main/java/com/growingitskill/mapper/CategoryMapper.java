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

import com.growingitskill.domain.CategoryVO;

public interface CategoryMapper {

	@Select("SELECT * FROM category")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	List<CategoryVO> listAll() throws Exception;

	@Insert("INSERT INTO category(term, slug_term, parent) VALUES(#{term}, #{slugTerm}, #{parent})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void create(CategoryVO categoryVO) throws Exception;
	
	@Select("SELECT * FROM category WHERE id = #{id}")
	@Results(@Result(property="slugTerm", column="slug_term"))
	CategoryVO readCategoryById(long id) throws Exception;

	@Update("UPDATE category SET term = #{term} WHERE id = #{id}")
	void updateTermById(@Param("id") long id, @Param("term") String term) throws Exception;

	@Update("UPDATE category SET slug_term = #{slugTerm} WHERE id = #{id}")
	void updateSlugTermById(@Param("id") long id, @Param("slugTerm") String slugTerm) throws Exception;
	
	@Update("UPDATE category SET parent = #{parent} WHERE id = #{id}")
	void updateParentById(@Param("id") long id, @Param("parent") long parent) throws Exception;

	@Delete("DELETE FROM category WHERE id IN " + "(SELECT id FROM "
			+ "(SELECT * FROM category WHERE id = #{id} OR id IN "
			+ "((SELECT id FROM category WHERE parent = #{id}))) as c)")
	void deleteById(long id) throws Exception;

}
