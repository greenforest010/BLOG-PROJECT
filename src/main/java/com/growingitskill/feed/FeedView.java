package com.growingitskill.feed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.growingitskill.domain.FeedEntry;
import com.growingitskill.domain.FeedInfo;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.atom.Person;
import com.rometools.rome.feed.synd.SyndPerson;

@Component
public class FeedView extends AbstractAtomFeedView {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedView.class);
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
		FeedInfo feedInfo = (FeedInfo) model.get("feedInfo");
		
		Content content = new Content();
		content.setValue(feedInfo.getSubtitle());
		
		Link link = new Link();
		
		if (feedInfo.getLink() != null) {
			String href = feedInfo.getLink().getProtocol() + "://" + feedInfo.getLink().getHost() + "/";
			
			link.setHref(href);
		}
		
		SyndPerson person = new Person();
		person.setName(feedInfo.getAuthor());
		
		feed.setId(feedInfo.getId());
		feed.setTitle(feedInfo.getTitle());
		feed.setSubtitle(content);
		feed.setAlternateLinks(Arrays.asList(link));
		feed.setAuthors(Arrays.asList(person));
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
			
			if (feedEntry.getLink() != null) {
				String href = feedEntry.getLink().getProtocol() + "://" + feedEntry.getLink().getHost() + feedEntry.getLink().getPath();
				
				link.setHref(href);
			}
			
			SyndPerson person = new Person();
			person.setName(feedEntry.getAuthor());
			
			Content content = new Content();
			content.setValue(feedEntry.getContent());
			content.setType(Content.HTML);
			
			entry.setId(feedEntry.getId());
			entry.setTitle(feedEntry.getTitle());
			entry.setAuthors(Arrays.asList(person));
			entry.setContents(Arrays.asList(content));
			entry.setAlternateLinks(Arrays.asList(link));
			entry.setUpdated(feedEntry.getUpdated());
			entry.setPublished(feedEntry.getPublished());
			
			entries.add(entry);
		}

		return entries;
	}

}
