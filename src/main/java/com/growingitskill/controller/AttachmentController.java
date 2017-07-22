package com.growingitskill.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.service.AttachmentService;
import com.growingitskill.util.UploadFileUtils;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AttachmentVO>> listAll() throws Exception {
		List<AttachmentVO> list = attachmentService.listAll();

		HttpStatus status = (list != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(list, status);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AttachmentVO> read(@PathVariable long id) throws Exception {
		AttachmentVO attachmentVO = attachmentService.findAttachmentById(id);

		HttpStatus status = (attachmentVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(attachmentVO, status);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestPart("file") MultipartFile file,
			UriComponentsBuilder uriComponentsBuilder) throws Exception {
		LOGGER.info("originalFilename: " + file.getOriginalFilename());
		LOGGER.info("contentType: " + file.getContentType());
		LOGGER.info("name: " + file.getName());
		LOGGER.info("byte: " + file.getBytes());
		LOGGER.info("size: " + file.getSize());

		String path = servletContext.getRealPath("/resources/upload");

		LOGGER.info("path: " + path);

		String fullName = UploadFileUtils.uploadFile(path, file.getOriginalFilename(), file.getBytes());

		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFullName(fullName);
		attachmentVO.setMimeType(file.getContentType());

		attachmentService.addAttachment(attachmentVO);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = uriComponentsBuilder.path("/attachments/").path(String.valueOf(attachmentVO.getId())).build()
				.toUri();

		headers.setLocation(locationUri);

		return new ResponseEntity<>(fullName, headers, HttpStatus.CREATED);
	}

}
