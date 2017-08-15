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

import com.growingitskill.domain.CategoryVO;
import com.growingitskill.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> listAll() throws Exception {
		List<CategoryVO> list = categoryService.listAll();
		
		HttpStatus status = (list != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(list, status);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryVO> read(@PathVariable long id) throws Exception {
		return responseFindCategoryById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CategoryVO> register(CategoryVO categoryVO, UriComponentsBuilder uriComponentsBuilder)
			throws Exception {
		categoryService.addCategory(categoryVO);

		HttpHeaders headers = new HttpHeaders();
		URI locationUri = uriComponentsBuilder.path("/categories/").path(String.valueOf(categoryVO.getId())).build()
				.toUri();

		headers.setLocation(locationUri);

		return new ResponseEntity<>(categoryVO, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<CategoryVO> modify(@PathVariable long id, @RequestBody Map<String, Object> map)
			throws Exception {

		if (map.get("term") != null) {
			String term = (String) map.get("term");

			categoryService.renameCategoryById(id, term);
		} else if (map.get("slugTerm") != null) {
			String slugTerm = (String) map.get("slugTerm");

			categoryService.modifyCategorySlugTermById(id, slugTerm);
		} else if (map.get("parent") != null) {
			long parent = Long.parseLong((String) map.get("parent"));

			categoryService.moveCategory(id, parent);
		}

		return responseFindCategoryById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> remove(@PathVariable long id) throws Exception {
		CategoryVO categoryVO = categoryService.findCategoryById(id);
		
		if (categoryVO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		categoryService.removeCategoryById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private ResponseEntity<CategoryVO> responseFindCategoryById(long id) throws Exception {
		CategoryVO categoryVO = categoryService.findCategoryById(id);

		HttpStatus status = (categoryVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(categoryVO, status);
	}

}
