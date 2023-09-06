package com.campusdual;

import java.util.Date;

public class Comment {

    //Atributes
    protected String text;
    protected Date date;
    protected User owner;
    //Constructor
    public Comment(String text, Date date, User owner) {
        this.text = text;
        this.date = date;
        this.owner = owner;
    }
    //Getters & Setters
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

}
