package com.campusdual;

import java.util.Date;

public class Img extends Post{

    //Atributes
    protected String title;
    protected String dimensions;

    //Constructor
    public Img(Date date, String title, String dimensions) {
        super(date);
        this.title=title;
        this.dimensions= dimensions;

    }

    //Getters & Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDimensions() {
        return dimensions;
    }
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    /************************************Methods*****************************/

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("\t]]]]]]]] Kind: Image [[[[[[[[[[");
        System.out.println("\tTitle: " + title);
        System.out.println("\tDimensions: " + dimensions);
        System.out.println("Comments:");
        for (Comment comment : commentList) {
            System.out.println("- " + comment.getText() + "\n\t (Owner: " + comment.getOwner().getName() + ")\n\t"+ comment.getDate());
        }
    }
}
