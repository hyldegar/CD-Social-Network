package com.campusdual;

import com.campusdual.util.Input;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SocialNet {


    public static Calendar calendar = Calendar.getInstance();
    public static Date date = calendar.getTime();

    public static void main(String[] args) {

        Initializer initializer = new Initializer();
        initializer.setUserList();
        List<User> users = initializer.getUserList();

        //proving ground
        /*for(User user: users){
            user.showDetails();
        }*/
        //Lists comments from user
        //getUserCommentsList(users, users.get(1));
        //Lists Posts from user
        //users.get(3).listPosts();
        //Follow a user
        //users.get(3).followUser(users.get(6));
        //Unfollow a user
        //users.get(5).unfollowUser(users.get(5).followList.get(1));
        //Add a user
        //users.add(new User("Manoliño"));
        //User showDetails
        //users.get(4).showDetails();


        System.out.println("\nFirst of all, this program is based on the good faith of the user");
        System.out.println("It is a learning exercise, therefore it will be full of bugs");
        Menus.showMenu(users);



    }

    /*public static void mainMenu() {

        int user = 0;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n-----------Main Menu-------------");
            System.out.println("1. Add users, posts or comments");
            System.out.println("2. Follow or unfollow users");
            System.out.println("3. Delete users, posts or comments");
            System.out.println("4. List all posts of user");
            System.out.println("5. List all comments of user");
            System.out.println("6. Number of comments on a post");
            System.out.println("7. User's wall");
            System.out.println("8. Friendship suggestions");
            System.out.println("9. Exit");
            System.out.print("Make your choice: ");
            int opcion = Input.integer();

            switch (opcion) {
                case 1:
                    Addmenu.showMenu(users, date);
                    //addMenu();
                    break;
                case 2:
                    followMenu();
                    break;
                case 3:
                    delMenu();
                    break;
                case 4:
                    listAllUsers(users);
                    user = Input.integer("Which user wants to list the posts?");
                    users.get(user-1).listPosts();
                    break;
                case 5:
                    listAllUsers(users);
                    user = Input.integer("Which user wants to list the posts?");
                    users.get(user-1).printCommentsFromUser(users);
                    break;
                case 6:
                    listAllUsers(users);
                    user = Input.integer("Which user wants to list the posts?");
                    System.out.println(" This user have: " + users.get(user-1).getPostList().size()+ " posts");
                    int number = Input.integer("Which post do you want to show the number of comments for?");
                    System.out.println("\nThe number of comments on this post is "+users.get(user-1).getPostList().get(number).obtainCommentsList());
                    break;
                case 7:
                    listAllUsers(users);
                    user = Input.integer("Which user do you want to show the wall for?");
                    users.get(user-1).showWall();
                    break;
                case 8:
                    listAllUsers(users);
                    user = Input.integer("Which user do you want to show friendship suggestions for?");
                    List<User> sugestedFriends= users.get(user-1).suggestFriendsWithCommonFriends();
                    System.out.println("************* Sugested Friends **************");
                    listAllUsers(sugestedFriends);
                    System.out.println("*********************************************");
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Enter a number from 1 to 9.");
                    break;
            }
        }

        System.out.println("¡See you soon!");
    }

    /*public static void addMenu(){
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
                    addPost(users.get(user-1));
                    break;
                case 3:
                    listAllUsers(users);
                    user = Input.integer("Which user wants to add comments?");
                    User selectedUser = users.get(user - 1);
                    System.out.println("This user have "+ selectedUser.getPostList().size());
                    int postNumber = Input.integer("Which post do you want to add comments to?");
                    Post selectedPost = selectedUser.getPostList().get(postNumber - 1);
                    String commentText = Input.string("Enter your comment: ");
                    Comment newComment = new Comment(commentText, date,selectedUser);
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
    public static void followMenu(){
        int user;
        boolean goback = false;

        while (!goback) {
            System.out.println("\nFollow Menu");
            System.out.println("1. Follow user");
            System.out.println("2. Unfollow user");
            System.out.println("3. Go Back");
            System.out.print("Make your choice: ");
            int opcion = Input.integer();
            switch (opcion) {
                case 1:
                    listAllUsers(users);
                    user = Input.integer("Enter your number of user: ");
                    int userToFollow = Input.integer("Enter the number of the user you want to follow: ");
                    users.get(user-1).followUser(users.get(userToFollow-1));
                    break;
                case 2:
                    listAllUsers(users);
                    user = Input.integer("Enter your number of user: ");
                    int userToUnfollow = Input.integer("Enter the number of the user you want to unfollow: ");
                    users.get(user-1).unfollowUser(users.get(userToUnfollow-1));
                    break;
                case 3:
                    goback = true;
                    break;
                default:
                    System.out.println("Invalid option. Enter a number from 1 to 3.");
                    break;
            }
        }
    }
    public static User findUserByName(String name){
        User userselected = null;
        for (User user : users){
            if(user.getName().equals(name) ){
                userselected= user;
                break;
            }
        }
        return userselected;
    }

    /*public static void addPost(User user) {
        System.out.println("Select the type of post:");
        System.out.println("1. Text Post");
        System.out.println("2. Image Post");
        System.out.println("3. Video Post");
        int postType = Input.integer("Enter the number of the post type: ");

        String content = "";
        String title = "";
        String dimensions = "";
        String videoQuality = "";
        int duration = 0;
        switch (postType) {
            case 1:
                content = Input.string("Enter the content of the text post: ");
                user.addPost(new Text(date,content));
                break;
            case 2:
                title = Input.string("Enter the title of the image: ");
                dimensions = Input.string("Enter the dimensions of the image: ");
                user.addPost(new Img(date,title, dimensions));
                break;
            case 3:
                title = Input.string("Enter the title of the video: ");
                videoQuality = Input.string("Enter the video quality: ");
                duration = Input.integer("Enter the duration of the video (in seconds): ");
                user.addPost(new Video(date,title, videoQuality, duration));
                break;
            default:
                System.out.println("Invalid post type.");
                break;
        }
    }
    public static void listAllUsers(List<User> users){
        int i=1;
        while(i <= users.size()){
            System.out.print(i + ". "+users.get(i-1).getName()+"\t\t");
            if (i>0 && i % 5 == 0) {
                System.out.print("\n");
            }
            i++;
        }
        System.out.println("\n------------------------------------");
    }
    public static void delMenu(){
        int user;
        boolean goback = false;

        while (!goback) {
            System.out.println("\nDelete Menu");
            System.out.println("1. Delete users");
            System.out.println("2. Delete posts");
            System.out.println("3. Delete Comment");
            System.out.println("4. Go Back");
            System.out.print("Make your choice: ");
            int opcion = Input.integer();
            switch (opcion) {
                case 1:
                    listAllUsers(users);
                    user = Input.integer("Enter the user you want to delete: ");
                    users.get(user-1).deleteUser(users);
                    break;
                case 2:
                    listAllUsers(users);
                    user = Input.integer("Enter the name of the user who wants to delete a post: ");
                    System.out.println("This user has "+ users.get(user-1).getPostList().size() + " posts");
                    int selectedPost= Input.integer("Enter the number of the post you want to delete");
                    users.get(user-1).deletePost(users.get(user-1).getPostList().get(selectedPost));

                    break;
                case 3:
                    listAllUsers(users);
                    user = Input.integer("Enter the name of the user who wants to delete a comment: ");
                    //List<Comment>  ;
                    System.out.println("This user has "+ users.get(user-1).getUserCommentsList(users).size() + " comments");
                    int selectedComment= Input.integer("Enter the number of the post you want to delete");

                    break;

                case 4:
                    goback = true;
                    break;
                default:
                    System.out.println("Invalid option. Enter a number from 1 to 4.");
                    break;
            }
        }
    }*/
}
