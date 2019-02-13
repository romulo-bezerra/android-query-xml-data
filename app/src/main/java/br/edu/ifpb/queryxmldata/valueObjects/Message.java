package br.edu.ifpb.queryxmldata.valueObjects;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Message implements Serializable {

    private String title;
    private URL link;
    private String dataHora;

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

    public String getData() {
        return dataHora;
    }

    public void setData(String dataHora) {
        this.dataHora = dataHora;
    }

//    @TargetApi(Build.VERSION_CODES.O)
//    private LocalDateTime getDataHora(String dataHora){
//        //2019-02-12T19:22:39Z
//        String dataSlice = dataHora.substring(0, 10);
//        String[] dataSplit = dataSlice.split("-");
//
//        String horarioSlice = dataHora.substring(11, 19);
//        String[] horarioSplit = horarioSlice.split(":");
//
//        int ano = Integer.parseInt(dataSplit[0]);
//        int mes = Integer.parseInt(dataSplit[1]);
//        int dia = Integer.parseInt(dataSplit[2]);
//        int hora = Integer.parseInt(horarioSplit[0]);
//        int minutos = Integer.parseInt(horarioSplit[1]);
//        int segundos = Integer.parseInt(horarioSplit[2]);
//
//        return LocalDateTime.of(ano, mes, dia, hora, minutos, segundos);
//    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", link=" + link +
                ", dataHora='" + dataHora + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(title, message.title) &&
                Objects.equals(link, message.link) &&
                Objects.equals(dataHora, message.dataHora);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, link, dataHora);
    }
}