package com.wole.story.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;

public class JsoupUtil {

	
	public static Document parse(String html){
		Document document=null;
		try {
			document=Jsoup.parse(html);
			
		} catch (Exception e) {
			e.printStackTrace();
			document=null;
		}
		return document;
	}
}
