package com.campusdual;

import java.util.Date;

public class Video extends Post{
    //Atributes
    protected String title;
    protected String quality;
    protected int duration;
    //Constructor
    public Video(Date date, String title, String quality, int duration) {
        super(date);
        this.title = title;
        this.quality=quality;
        this.duration=duration;
    }
    //Getters & Setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }


    /***************************Methods************************************/

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("\t}}}}}}}}}}}}} Kind: Video {{{{{{{{{{{{{{{");
        System.out.println("\tTitle of the Video: " + title);
        System.out.println("\tQuality of the Video: " + quality);
        System.out.println("\tDuration of the Video: " + duration + " seconds");
        System.out.println("Comments:");
        for (Comment comment : commentList) {
            System.out.println("- " + comment.getText() + "\n\t (Owner: " + comment.getOwner().getName() + ")\n\t"+ comment.getDate());
        }
    }
}
