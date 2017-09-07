package com.growingitskill.util;

import org.springframework.stereotype.Component;

import com.github.slugify.Slugify;

@Component
public class SlugUtils {
	
	public String slug(String text) {
		Slugify slugify = new Slugify();

		return slugify.slugify(text);
	}

}
