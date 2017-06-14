package com.growingitskill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.growingitskill.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	// HTTP DELETE 메서드 요청시 body가 없으므로 /url?ids=?,?,? 형식으로 테스트 가능
	@RequestMapping(method = RequestMethod.DELETE)
	public void removePosts(@RequestParam("ids") long[] ids) throws Exception {
		postService.removeByIds(ids);
	}

}
