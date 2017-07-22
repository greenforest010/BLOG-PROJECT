package com.growingitskill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.mapper.AttachmentMapper;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public List<AttachmentVO> listAll() throws Exception {
		return attachmentMapper.listAll();
	}

	@Override
	public void addAttachment(AttachmentVO attachmentVO) throws Exception {
		attachmentMapper.create(attachmentVO);
	}

	@Override
	public AttachmentVO findAttachmentById(long id) throws Exception {
		return attachmentMapper.readAttachmentById(id);
	}

	@Override
	public void modifyAlternateTextById(long id, String alternateText) throws Exception {
		attachmentMapper.updateAlternateTextById(id, alternateText);
	}

	@Override
	public void modifyDescriptionById(long id, String description) throws Exception {
		attachmentMapper.updateDescriptionById(id, description);
	}

	@Override
	public void removeAttachmentByIds(long[] ids) throws Exception {
		attachmentMapper.deleteByIds(ids);
	}
	
}
