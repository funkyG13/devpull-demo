package com.devpull.demo.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LanguagesTest {

	@Test
	public void testLanguagesGettersAndSetters() {
		Languages languages = new Languages();
		languages.setId(1);
		languages.setLanguageName("Java");
		languages.toString();
		assertEquals("Id: ", 1, languages.getId());
		assertEquals("Language Name: ", "Java", languages.getLanguageName());
	}

}
