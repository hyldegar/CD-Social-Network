package com.campusdual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {

    //Atributes
    protected Date date;
    protected List<Comment> commentList;


    //Constructor
    public Post(Date date) {
        this.date = date;
        this.commentList = new ArrayList<>();
    }

    //Getters and Setters
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public List<Comment> getCommentList() {
        return commentList;
    }
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    /************************Methods*************************/

    // Method for add a Comment
    public void addComment(Comment comment) {
        commentList.add(comment);
    }
    // Method for obtain size of Comment list in a Post
    public int obtainCommentsList() {
        return commentList.size();
    }
    // Method for delete a Comment
    public void deleteComment(Comment comment) {
        if (commentList.contains(comment)) {
            commentList.remove(comment);
        }
    }
    //Method to show details of the Post
    public void showDetails() {
        System.out.println("\n##Date of the Post##\n## " + this.date+" ##");
    }
}
