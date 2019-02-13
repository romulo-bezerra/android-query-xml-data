package br.edu.ifpb.queryxmldata.valueObjects;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Message implements Serializable {

    private String title;
    private URL link;

    public Message(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(String link) {
        try {
            this.link = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", link=" + link +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(title, message.title) &&
                Objects.equals(link, message.link);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, link);
    }
}