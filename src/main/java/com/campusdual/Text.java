package com.campusdual;

import java.util.Date;

public class Text extends Post{
    //Atributes
    protected String text;
    //Constructor
    public Text(Date date, String text) {
        super(date);
        this.text = text;
    }
    //Getters & Setters
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    /***********************Methods***************************/

    //Method for show details of a text post
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("\t------Kind: Text------");
        System.out.println("\tContent: " + text);
        System.out.println("Comments:");
        for (Comment comment : commentList) {
            System.out.println("- " + comment.getText() + "\n\t (Owner: " + comment.getOwner().getName() + ")\n\t"+ comment.getDate());
        }
    }
}
