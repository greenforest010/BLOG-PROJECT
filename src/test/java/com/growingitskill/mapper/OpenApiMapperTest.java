package com.growingitskill.mapper;

import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.growingitskill.config.MybatisJndiConfig;
import com.growingitskill.domain.NaverPapagoNMT;
import com.growingitskill.domain.OpenApi;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisJndiConfig.class)
public class OpenApiMapperTest {

	@BeforeClass
	public static void jndiBind() throws NamingException {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/test");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		builder.bind("jdbc/test", dataSource);
	}

	@Autowired
	private OpenApiMapper openApiMapper;

	@Test
	public void findByApiName() throws Exception {
		String apiName = "Papago NMT";

		NaverPapagoNMT openApi = openApiMapper.readByApiName(apiName);

		System.out.println(openApi);
	}

	@Test
	public void testdy() throws Exception {
		String test = "카테고리 하하하 호호호 =-=-=-=";

		String result = translate(test, "ko", "en");

		System.out.println(result);
	}

	private String translate(String text, String source, String target) throws Exception {
		String apiName = "Papago NMT";

		NaverPapagoNMT naverPapagoNMT = openApiMapper.readByApiName(apiName);

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
