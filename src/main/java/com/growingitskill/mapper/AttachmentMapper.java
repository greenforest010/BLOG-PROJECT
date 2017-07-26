package com.growingitskill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.sqlprovider.AttachmentSqlProvider;

public interface AttachmentMapper {

	@Select("SELECT * FROM attachment")
	@Results({ @Result(property = "mimeType", column = "mime_type"),
			@Result(property = "alternateText", column = "alternate_text") })
	List<AttachmentVO> listAll() throws Exception;

	@Insert("INSERT INTO attachment(fullname, mime_type) VALUES(#{fullName}, #{mimeType})")
	@SelectKey(statement = { "SELECT LAST_INSERT_ID()" }, keyProperty = "id", before = false, resultType = long.class)
	void create(AttachmentVO attachmentVO) throws Exception;

	@Select("SELECT * FROM attachment WHERE id = #{id}")
	@Results({ @Result(property = "mimeType", column = "mime_type"),
			@Result(property = "alternateText", column = "alternate_text") })
	AttachmentVO readAttachmentById(long id) throws Exception;

	@Update("UPDATE attachment SET alternate_text = #{alternateText} WHERE id = #{id}")
	void updateAlternateTextById(@Param("id") long id, @Param("alternateText") String alternateText) throws Exception;

	@Update("UPDATE attachment SET description = #{description} WHERE id = #{id}")
	void updateDescriptionById(@Param("id") long id, @Param("description") String description) throws Exception;

	@DeleteProvider(type = AttachmentSqlProvider.class, method = "deleteByIds")
	void deleteByIds(@Param("ids") long[] ids) throws Exception;

}
