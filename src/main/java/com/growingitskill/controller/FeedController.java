package com.growingitskill.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.growingitskill.domain.BlogInfo;
import com.growingitskill.domain.FeedEntry;
import com.growingitskill.domain.FeedInfo;
import com.growingitskill.domain.PostVO;
import com.growingitskill.domain.TagVO;
import com.growingitskill.service.BlogInfoService;
import com.growingitskill.service.PostService;
import com.growingitskill.service.TagService;

@Controller
@RequestMapping("/feed")
public class FeedController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private BlogInfoService blogInfoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String feed(Model model) throws Exception {
		model.addAttribute("feedInfo", getFeedInfo());
		
		return "feedView";
	}
	
	private FeedInfo getFeedInfo() throws Exception {
		FeedInfo feedInfo = new FeedInfo();
		
		BlogInfo blogInfo = blogInfoService.findBlogInfo();
		
		final String FEED_TITLE = blogInfo.getTitle();
		final String FEED_SUBTITLE = blogInfo.getSubtitle();
		final String FEED_RIGHTS = blogInfo.getRights();
		final String INDEX_URL = "http://www." + blogInfo.getDomainName() + "/";
		final String INDEX_POST_URL = INDEX_URL + "post/";
		
		feedInfo.setId(INDEX_URL);
		feedInfo.setTitle(FEED_TITLE);
		feedInfo.setSubtitle(FEED_SUBTITLE);
		feedInfo.setRights(FEED_RIGHTS);
		
		List<PostVO> posts = postService.findPosts();

		List<FeedEntry> feedEntries = new ArrayList<>();
		
		for (PostVO postVO : posts) {
			FeedEntry feedEntry = new FeedEntry();
			
			List<TagVO> tags = tagService.findTagByPostId(postVO.getId());
			
			feedEntry.setId(INDEX_POST_URL + postVO.getSlugTitle());
			feedEntry.setTitle(postVO.getTitle());
			feedEntry.setUpdated(postVO.getUpdated());
			feedEntry.setPublished(postVO.getPublished());
			feedEntry.setTagVOs(tags);
			
			feedEntries.add(feedEntry);
		}

		feedInfo.setFeedEntries(feedEntries);
		
		return feedInfo;
	}

}
