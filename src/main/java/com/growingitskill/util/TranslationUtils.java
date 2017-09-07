package com.growingitskill.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.growingitskill.domain.NaverPapagoNMT;
import com.growingitskill.service.OpenApiService;

@Component
public class TranslationUtils {

	@Autowired
	private OpenApiService openApiService;

	public String translate(String text, String source, String target) throws Exception {
		String apiName = "Papago NMT";

		NaverPapagoNMT naverPapagoNMT = openApiService.findNaverPapagoNMTByApiName(apiName);

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
