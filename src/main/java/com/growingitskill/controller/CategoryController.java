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
import com.growingitskill.util.SlugUtils;
import com.growingitskill.util.TranslationUtils;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	public static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TranslationUtils translationUtils;
	
	@Autowired
	private SlugUtils slugUtils;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> findCategories() throws Exception {
		List<CategoryVO> list = categoryService.findCategories();

		HttpStatus status = (list != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(list, status);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryVO> findCategory(@PathVariable long id) throws Exception {
		return responseFindCategory(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CategoryVO> registerCategory(CategoryVO categoryVO, UriComponentsBuilder uriComponentsBuilder)
			throws Exception {
		categoryService.addCategory(categoryVO);

		URI locationUri = uriComponentsBuilder.path("/categories/").path(String.valueOf(categoryVO.getId())).build()
				.toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(locationUri);

		return new ResponseEntity<>(categoryVO, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<CategoryVO> modifyCategory(@PathVariable long id, @RequestBody Map<String, Object> map)
			throws Exception {
		String term = (String) map.get("term");
		String slugTerm = (String) map.get("slugTerm");
		String parentObject = (String) map.get("parent");

		if (term != null) {
			slugTerm = slugUtils.slug(translationUtils.translate(term, "ko", "en"));

			categoryService.renameCategoryById(id, term, slugTerm);
		} else if (slugTerm != null) {
			categoryService.modifyCategorySlugTermById(id, slugTerm);
		} else if (parentObject != null) {
			long parent = Long.parseLong(parentObject);
			
			categoryService.moveCategory(id, parent);
		}

		return responseFindCategory(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeCategory(@PathVariable long id) throws Exception {
		CategoryVO categoryVO = categoryService.findCategoryById(id);

		if (categoryVO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		categoryService.removeCategoryById(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	private ResponseEntity<CategoryVO> responseFindCategory(long id) throws Exception {
		CategoryVO categoryVO = categoryService.findCategoryById(id);

		HttpStatus status = (categoryVO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<>(categoryVO, status);
	}

}
