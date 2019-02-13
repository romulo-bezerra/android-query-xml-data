package br.edu.ifpb.queryxmldata.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.queryxmldata.util.XmlPullFeedParser;
import br.edu.ifpb.queryxmldata.valueObjects.Message;

public class ServicePullFeed extends IntentService {

    private final String URL_FEEDRSS_DIARIODOSERTAO = "https://www.diariodosertao.com.br/feed/atom";
    private List<Message> messages = new ArrayList<>();

    public ServicePullFeed() {
        super("ServicePullFeed");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        XmlPullFeedParser xmlPullFeedParser = new XmlPullFeedParser(URL_FEEDRSS_DIARIODOSERTAO);
        messages = xmlPullFeedParser.parse();

        intent.putExtra("messages", (Serializable) messages);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(intent);
    }

}