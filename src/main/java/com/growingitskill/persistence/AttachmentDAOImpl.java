package com.growingitskill.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.mapper.AttachmentMapper;

public class AttachmentDAOImpl extends SqlSessionDaoSupport implements AttachmentMapper {

	private final String namespace = "com.growingitskill.mapper.AttachmentMapper";

	@Override
	public List<AttachmentVO> listAll() throws Exception {
		return getSqlSession().selectList(namespace + ".listAll");
	}

	@Override
	public void create(AttachmentVO attachmentVO) throws Exception {
		getSqlSession().insert(namespace + ".create", attachmentVO);
	}

	@Override
	public AttachmentVO readAttachmentById(long id) throws Exception {
		return getSqlSession().selectOne(namespace + ".readAttachmentById", id);
	}

	@Override
	public void updateAlternateTextById(long id, String alternateText) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("alternateText", alternateText);

		getSqlSession().update(namespace + ".updateAlternateTextById", map);
	}

	@Override
	public void updateDescriptionById(long id, String description) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("description", description);

		getSqlSession().update(namespace + ".updateDescriptionById", map);
	}

	@Override
	public void deleteByIds(long[] ids) throws Exception {
		getSqlSession().delete(namespace + ".deleteByIds", ids);
	}

}
