package com.growingitskill.service;

import java.util.List;

import com.growingitskill.domain.AttachmentVO;

public interface AttachmentService {
	
	List<AttachmentVO> listAll() throws Exception;
	
	void addAttachment(AttachmentVO attachmentVO) throws Exception;
	
	AttachmentVO findAttachmentById(long id) throws Exception;
	
	void modifyAlternateTextById(long id, String alternateText) throws Exception;
	
	void modifyDescriptionById(long id, String description) throws Exception;
	
	void removeAttachmentByIds(long[] ids)throws Exception;

}
