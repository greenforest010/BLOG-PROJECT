package com.growingitskill.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.growingitskill.domain.TagVO;
import com.growingitskill.service.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TagVO>> listAll() throws Exception {
		List<TagVO> list = tagService.findTagList();

		HttpStatus status = (list != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(list, status);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TagVO> register(TagVO tagVO, UriComponentsBuilder uriComponentsBuilder) throws Exception {
		tagVO.setSlugTerm(tagVO.getTerm());

		tagService.addTag(tagVO);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = uriComponentsBuilder.path("/tags/").path(String.valueOf(tagVO.getId())).build().toUri();

		headers.setLocation(locationUri);

		return new ResponseEntity<>(tagVO, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<TagVO> modify(@PathVariable long id, @RequestBody Map<String, Object> map) throws Exception {
		if (map.get("term") != null) {
			String term = (String) map.get("term");
			
			tagService.modifyTermById(id, term);
		} else if (map.get("slugTerm") != null) {
			String slugTerm = (String) map.get("slugTerm");
			
			tagService.modifySlugTermById(id, slugTerm);
		}
		
		TagVO tagVO = tagService.findTagById(id);
		
		HttpStatus status = (tagVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<>(tagVO, status);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> remove(@PathVariable long id) throws Exception {
		TagVO tagVO = tagService.findTagById(id);
		
		if (tagVO == null) {
			return new 	ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		tagService.removeTagById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
