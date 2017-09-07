package com.growingitskill.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.growingitskill.domain.TagVO;
import com.growingitskill.service.TagService;
import com.growingitskill.util.SlugUtils;
import com.growingitskill.util.TranslationUtils;

@RestController
@RequestMapping("/tags")
public class TagController {

	public static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagService tagService;

	@Autowired
	private TranslationUtils translationUtils;

	@Autowired
	private SlugUtils slugUtils;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TagVO>> listAll() throws Exception {
		List<TagVO> list = tagService.findTags();

		HttpStatus status = (list != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(list, status);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<TagVO> read(@PathVariable("id") long id) throws Exception {
		TagVO tagVO = tagService.findTagById(id);

		HttpStatus status = (tagVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(tagVO, status);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TagVO> register(TagVO tagVO, UriComponentsBuilder uriComponentsBuilder) throws Exception {

		tagVO.setSlugTerm(slugUtils.slug(translationUtils.translate(tagVO.getTerm(), "ko", "en")));

		tagService.addTag(tagVO);
		
		URI locationUri = uriComponentsBuilder.path("/tags/").path(String.valueOf(tagVO.getId())).build().toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(locationUri);

		return new ResponseEntity<>(tagVO, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<TagVO> modify(@PathVariable long id, @RequestBody Map<String, Object> map) throws Exception {
		String term = (String) map.get("term");
		String slugTerm = (String) map.get("slugTerm");
		
		if (term != null) {
			slugTerm = slugUtils.slug(translationUtils.translate(term, "ko", "en"));
			
			tagService.modifyTagTermAndSlugTermById(id, term, slugTerm);
		} else if (slugTerm != null) {
			tagService.modifyTagSlugTermById(id, slugTerm);
		}

		TagVO tagVO = tagService.findTagById(id);

		HttpStatus status = (tagVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(tagVO, status);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> remove(@PathVariable long id) throws Exception {
		TagVO tagVO = tagService.findTagById(id);

		if (tagVO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		tagService.removeTagById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
