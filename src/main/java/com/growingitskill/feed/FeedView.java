package com.growingitskill.feed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.growingitskill.domain.FeedEntry;
import com.growingitskill.domain.FeedInfo;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;

@Component
public class FeedView extends AbstractAtomFeedView {
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
		FeedInfo feedInfo = (FeedInfo) model.get("feedInfo");
		
		Content content = new Content();
		content.setValue(feedInfo.getSubtitle());
		
		feed.setId(feedInfo.getId());
		feed.setTitle(feedInfo.getTitle());
		feed.setSubtitle(content);
		feed.setCopyright(feedInfo.getRights());
	}

	@Override
	protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FeedInfo feedInfo = (FeedInfo) model.get("feedInfo");
		
		List<Entry> entries = new ArrayList<>();
		
		for (FeedEntry feedEntry : feedInfo.getFeedEntries()) {
			Entry entry = new Entry();
			
			Link link = new Link();
			link.setHref(feedEntry.getLink());
			
			entry.setId(feedEntry.getId());
			entry.setTitle(feedEntry.getTitle());
			entry.setUpdated(feedEntry.getUpdated());
			entry.setPublished(feedEntry.getPublished());
			
			entries.add(entry);
		}

		return entries;
	}

}
