package br.edu.ifpb.queryxmldata.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import br.edu.ifpb.queryxmldata.interfaces.FeedParser;

public abstract class BaseFeedParser implements FeedParser {

    // names of the XML tags
    static final  String TITLE = "title";
    static final String LINK = "link";
    static final  String ENTRY = "entry";

    final URL feedUrl;

    protected BaseFeedParser(String feedUrl){
        try {
            this.feedUrl = new URL(feedUrl);
        } catch (MalformedURLException e) {
            Log.e("Error: ", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInputStream() {
        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}