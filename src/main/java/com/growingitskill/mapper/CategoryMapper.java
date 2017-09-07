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

import com.growingitskill.domain.CategoryLevel;
import com.growingitskill.domain.CategoryVO;

public interface CategoryMapper {

	@Select("SELECT * FROM category")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	List<CategoryVO> readCategories() throws Exception;

	@Insert("INSERT INTO category(term, slug_term, parent) VALUES(#{term}, #{slugTerm}, #{parent})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void createCategory(CategoryVO categoryVO) throws Exception;

	@Select("SELECT * FROM category WHERE id = #{id}")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	CategoryVO readCategoryById(long id) throws Exception;
	
	@Select("SELECT * FROM category WHERE slug_term LIKE #{slugTerm}")
	@Results(@Result(property = "slugTerm", column = "slug_term"))
	CategoryVO readCategoryBySlugTerm(String slugTerm) throws Exception;

	@Update("UPDATE category SET term = #{term}, slug_term = #{slugTerm} WHERE id = #{id}")
	void updateCategoryTermAndSlugTermById(@Param("id") long id, @Param("term") String term, @Param("slugTerm") String slugTerm) throws Exception;

	@Update("UPDATE category SET slug_term = #{slugTerm} WHERE id = #{id}")
	void updateCategorySlugTermById(@Param("id") long id, @Param("slugTerm") String slugTerm) throws Exception;

	@Update("UPDATE category SET parent = #{parent} WHERE id = #{id}")
	void updateCategoryParentById(@Param("id") long id, @Param("parent") long parent) throws Exception;

	@Delete("DELETE FROM category WHERE id = #{id}")
	void deleteCategoryById(long id) throws Exception;

	@Select("SELECT c1.* FROM category c1 LEFT JOIN category c2 ON c1.id = c2.parent WHERE c2.id IS NULL")
	List<CategoryVO> readLeafCategories() throws Exception;

	@Select("SELECT c1.id AS level1, c2.id AS level2, c3.id AS level3, c4.id AS level4 FROM category c1 LEFT JOIN category c2 ON c2.parent = c1.id LEFT JOIN category c3 ON c3.parent = c2.id LEFT JOIN category c4 ON c4.parent = c3.id WHERE c1.slug_term LIKE #{slugTerm}")
	List<CategoryLevel> readCategoryLevel(String slugTerm) throws Exception;

}
