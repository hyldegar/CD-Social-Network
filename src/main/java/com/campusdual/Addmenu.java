package com.campusdual;

import com.campusdual.util.Input;

import java.util.Date;
import java.util.List;

public class Addmenu {
    public static void showMenu(List<User> users, Date date) {

        int user;
        boolean goback = false;

        while (!goback) {
            System.out.println("\nAdd Menu");
            System.out.println("1. Add users");
            System.out.println("2. Add posts");
            System.out.println("3. Add Comment");
            System.out.println("4. Go Back");
            System.out.print("Make your choice: ");
            int opcion = Input.integer();
            switch (opcion) {
                case 1:
                    String newUser = Input.string("Enter the user you want to add: ");
                    users.add(new User(newUser));
                    break;
                case 2:
                    listAllUsers(users);
                    user = Input.integer("Enter the number of the user who wants to add a post: ");
                    users.get(user - 1).addPost(date);
                    break;
                case 3:
                    listAllUsers(users);
                    user = Input.integer("Which user wants to add comments?");
                    User selectedUser = users.get(user - 1);
                    int userOwner= Input.integer("Which user is going to make the comment?");
                    User userOwnerC= users.get(userOwner-1);
                    System.out.println("This user have " + selectedUser.getPostList().size());
                    int postNumber = Input.integer("Which post do you want to add comments to?");
                    Post selectedPost = selectedUser.getPostList().get(postNumber - 1);
                    String commentText = Input.string("Enter your comment: ");
                    Comment newComment = new Comment(commentText, date, userOwnerC);
                    selectedPost.addComment(newComment);
                    break;
                case 4:
                    goback = true;
                    break;
                default:
                    System.out.println("Invalid option. Enter a number from 1 to 4.");
                    break;
            }
        }


    }

    public static void listAllUsers(List<User> users) {
        int i = 1;
        while (i <= users.size()) {
            System.out.print(i + ". " + users.get(i - 1).getName() + "\t\t");
            if (i > 0 && i % 5 == 0) {
                System.out.print("\n");
            }
            i++;
        }
        System.out.println("\n------------------------------------");
    }
}
