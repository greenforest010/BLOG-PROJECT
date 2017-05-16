package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.growingitskill.domain.CategoryVO;

public interface CategoryMapper {
	
	@Select("SELECT * FROM category")
	@Results(@Result(property="slugTerm", column="slug_term"))
	List<CategoryVO> listAll() throws Exception;

}
