package br.edu.ifpb.queryxmldata.interfaces;

import java.util.List;

import br.edu.ifpb.queryxmldata.valueObjects.Message;

public interface FeedParser {
    List<Message> parse();
}