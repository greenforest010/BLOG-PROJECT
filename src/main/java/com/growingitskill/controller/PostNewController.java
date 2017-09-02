package com.growingitskill.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.growingitskill.domain.CategoryVO;
import com.growingitskill.domain.Criteria;
import com.growingitskill.domain.NaverPapagoNMT;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.TagVO;
import com.growingitskill.service.CategoryService;
import com.growingitskill.service.MemberService;
import com.growingitskill.service.OpenApiService;
import com.growingitskill.service.PostService;
import com.growingitskill.service.TagService;

@Controller
@RequestMapping("/admin/post/new")
public class PostNewController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PostNewController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private PostService postService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TagService tagService;

	@Autowired
	private OpenApiService openApiService;

	@RequestMapping(method = RequestMethod.GET)
	public void newPage(@ModelAttribute Criteria criteria, Model model) throws Exception {
		model.addAttribute("categoryList", categoryService.listLeafCategory());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String newPagePOST(PostVO postVO, @RequestParam("loginId") String loginId,
			@RequestParam("categoryId") long categoryId, @RequestParam("tags") String tags,
			RedirectAttributes redirectAttributes) throws Exception {
		LOGGER.info("tags[" + tags + "]");

		if (tags != null && !tags.isEmpty()) {
			String[] tagTerms = tags.split(",");

			// 새로운 태그들 생성
			List<String> existentTagTermList = tagService.findTagTermByTerms(tagTerms);

			List<String> newTermList = new ArrayList<>();
			Collections.addAll(newTermList, tagTerms);

			for (String term : existentTagTermList) {
				newTermList.remove(term);
			}

			List<TagVO> newTagList = new ArrayList<>();

			for (String term : newTermList) {
				TagVO tagVO = new TagVO();
				tagVO.setTerm(term);

				String translateSlugTerm = translate(term, "ko", "en");

				tagVO.setSlugTerm(translateSlugTerm);

				newTagList.add(tagVO);
			}

			if (newTagList.size() > 0) {
				tagService.addTags(newTagList);
			}

			// 글-태그 연결
			List<TagVO> tagList = new ArrayList<>();

			List<Long> tagIdList = tagService.findTagIdByTerms(tagTerms);

			for (Long id : tagIdList) {
				TagVO tagVO = new TagVO();
				tagVO.setId(id);

				tagList.add(tagVO);
			}

			postVO.setTagList(tagList);
		}

		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setId(categoryId);

		postVO.setCategoryVO(categoryVO);

		postVO.setAuthor(memberService.findIdByLoginId(loginId));

		String translatedSlugTitle = translate(postVO.getSlugTitle(), "ko", "en");

		postVO.setSlugTitle(translatedSlugTitle);

		LOGGER.info(postVO.toString());

		postService.regist(postVO);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/post";
	}

	private String translate(String text, String source, String target) throws Exception {
		String apiName = "Papago NMT";

		NaverPapagoNMT naverPapagoNMT = openApiService.findByApiName(apiName);

		LOGGER.info(naverPapagoNMT.toString());

		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiURL);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("source", source);
		params.add("target", target);
		params.add("text", text);

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-Naver-Client-Id", naverPapagoNMT.getClientId());
		headers.add("X-Naver-Client-Secret", naverPapagoNMT.getClientSecret());

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
				requestEntity, String.class);

		LOGGER.info("response: " + response);

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode root = (ObjectNode) mapper.readTree(response.getBody());
		ObjectNode message = (ObjectNode) root.get("message");

		String translatedText = "";

		if (message.isMissingNode()) {

		} else {
			ObjectNode result = (ObjectNode) message.get("result");
			translatedText = result.get("translatedText").asText();
		}

		return translatedText;
	}

}
