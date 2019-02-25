package com.codecool.krk.model;

import java.util.Date;

public class Message {

    private String user;
    private Date date;
    private String text;

    public Message(String user,  Date date, String text){
        this.user = user;
        this.date = date;
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
