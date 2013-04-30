package org.istanbulhs.istanbulhackerspaceapp.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.istanbulhs.istanbulhackerspaceapp.data.RssEntity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class WordpressRssParser {

	private static final String ns = null;
	   
	public List<RssEntity> parse(InputStream in) throws XmlPullParserException, IOException {
	        try {
	            XmlPullParser parser = Xml.newPullParser();
	            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	            parser.setInput(in, null);
	            parser.nextTag();
	            return readFeed(parser);
	        } finally {
	            in.close();
	        }
	 }
	
	private List<RssEntity> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
	    List<RssEntity> entries = new ArrayList<RssEntity>();

	    parser.require(XmlPullParser.START_TAG, ns, "rss");
	    
	    
	    
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        
	        if ("channel".equals(name)) {
		    	parser.next();
		    	continue;
		    }
	        
	        Log.i("hs", "Tag name:" + name);
	        
	        if ("item".equals(name)) {
	            entries.add(readEntry(parser));
	        } else {
	            skip(parser);
	        }
	    }  
	    return entries;
	}
	
	private RssEntity readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "item");
	    String title = null;
	    String summary = null;
	    String link = null;
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if ("title".equals(name)) {
	            title = readTitle(parser);
	        } else if ("description".equals(name)) {
	            summary = readDescription(parser);
	        } else if ("link".equals(name)) {
	            link = readLink(parser);
	        } else {
	            skip(parser);
	        }
	    }
	    return new RssEntity(title, link, summary);
	}

	// Processes title tags in the feed.
	private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "title");
	    String title = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "title");
	    return title;
	}
	  
	// Processes link tags in the feed.
	private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "link");
	    String link = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "link");
	    return link;
	}

	// Processes summary tags in the feed.
	private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "description");
	    String summary = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "description");
	    return summary;
	}

	// For the tags title and summary, extracts their text values.
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}

	 
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
}
