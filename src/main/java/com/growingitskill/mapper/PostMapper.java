package com.growingitskill.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.SearchCriteria;
import com.growingitskill.sqlprovider.PostSqlProvider;

public interface PostMapper {

	@Insert("INSERT INTO post(title, author, content, slug_title) VALUES(#{title}, #{author}, #{content}, #{slugTitle})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void createPost(PostVO postVO) throws Exception;

	/*
	 * xml mapper로 대체
	 */
	PostVO readPostById(long id) throws Exception;

	PostVO readPostBySlugTitle(String slugTitle) throws Exception;

	@Update("UPDATE post SET title = #{title}, content = #{content}, slug_title = #{slugTitle} where id = #{id}")
	void updatePost(PostVO postVO) throws Exception;

	@DeleteProvider(type = PostSqlProvider.class, method = "delete")
	void deletePostByIds(@Param("ids") long[] ids) throws Exception;

	/*
	 * Join 한 테이블의 association에 해당하는 @ONE annotation을 쓴 결과가 제대로 나오지 않음. -> xml
	 * mapper로 대체
	 */
	List<PostVO> readPosts() throws Exception;

	/*
	 * xml mapper로 대체
	 */
	List<PostVO> readPostsWithCriteria(SearchCriteria searchCriteria) throws Exception;

	@Select("SELECT COUNT(id) FROM post WHERE id > 0 AND title LIKE CONCAT('%', #{keyword}, '%') OR content LIKE CONCAT('%', #{keyword}, '%')")
	int countPostWithCriteriaPaging(SearchCriteria searchCriteria) throws Exception;

	/*
	 * xml mapper로 대체
	 */
	List<PostVO> readPostsWithCriteriaByCategory(@Param("categoryLevelSet") Set<Long> categoryLevelSet,
			@Param("criteria") SearchCriteria searchCriteria) throws Exception;

	/*
	 * xml mapper로 대체
	 */
	int countPostPagingByCategory(@Param("categoryLevelSet") Set<Long> categoryLevelSet) throws Exception;

	/*
	 * xml mapper로 대체
	 */
	List<PostVO> readPostsWithCriteriaByTag(@Param("slugTerm") String slugTerm, @Param("criteria") SearchCriteria searchCriteria)
			throws Exception;

	/*
	 * xml mapper로 대체
	 */
	int countPostWithCriteriaPagingByTag(@Param("slugTerm") String slugTerm, @Param("criteria") SearchCriteria searchCriteria)
			throws Exception;
}
