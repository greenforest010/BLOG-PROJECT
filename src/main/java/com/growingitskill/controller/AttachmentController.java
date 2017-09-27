package com.growingitskill.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.growingitskill.config.WebAppInitializer;
import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.service.AttachmentService;
import com.growingitskill.util.UploadFileUtils;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AttachmentVO>> findAttachments() throws Exception {
		List<AttachmentVO> list = attachmentService.listAll();

		HttpStatus status = (list != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(list, status);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AttachmentVO> findAttachment(@PathVariable long id) throws Exception {
		return responseFindAttachment(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> registerAttachment(@RequestPart("file") MultipartFile file,
			UriComponentsBuilder uriComponentsBuilder) throws Exception {
		printUploadFileInfo(file);

		String fullName = UploadFileUtils.uploadFile(getUploadPath(),
				file.getOriginalFilename(), file.getBytes());

		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFullName(fullName);
		attachmentVO.setMimeType(file.getContentType());

		attachmentService.addAttachment(attachmentVO);

		URI locationUri = uriComponentsBuilder.path("/attachments/").path(String.valueOf(attachmentVO.getId())).build()
				.toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(locationUri);

		return new ResponseEntity<>(fullName, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<AttachmentVO> modifyAttachment(@PathVariable long id, @RequestBody Map<String, String> map)
			throws Exception {
		
		String alternateText = map.get("alternateText");
		String description = map.get("description");

		if (alternateText != null) {
			attachmentService.modifyAlternateTextById(id, alternateText);
		}

		if (description != null) {
			attachmentService.modifyDescriptionById(id, description);
		}

		return responseFindAttachment(id);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void removeAttachment(@RequestParam("ids") long[] ids) throws Exception {
		for (long id : ids) {
			AttachmentVO attachmentVO = attachmentService.findAttachmentById(id);

			if (attachmentVO != null) {
				new File(getUploadPath() + attachmentVO.getFullName()).delete();
			}
		}

		attachmentService.removeAttachmentByIds(ids);
	}
	
	private String getUploadPath() {		
		return WebAppInitializer.uploadPath;
	}

	private ResponseEntity<AttachmentVO> responseFindAttachment(long id) throws Exception {
		AttachmentVO attachmentVO = attachmentService.findAttachmentById(id);

		HttpStatus status = (attachmentVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(attachmentVO, status);
	}

	private void printUploadFileInfo(MultipartFile file) throws IOException {
		LOGGER.info("originalFilename: " + file.getOriginalFilename());
		LOGGER.info("contentType: " + file.getContentType());
		LOGGER.info("name: " + file.getName());
		LOGGER.info("byte: " + file.getBytes());
		LOGGER.info("size: " + file.getSize());
	}

}
