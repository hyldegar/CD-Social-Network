package com.campusdual;

import com.campusdual.util.Input;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    //Atributes
    protected String name;
    protected List<User> followList;
    protected List<Post> postList;


    //Constructor
    public User(String name) {
        this.name = name;
        this.followList = new ArrayList<>();
        this.postList = new ArrayList<>();
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowList() {
        return followList;
    }

    public void setFollowList(List<User> followList) {
        this.followList = followList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    /********************************METHODS*********************/

    // Method to follow a User
    public void followUser(User user) {
        if (!followList.contains(user)) {
            followList.add(user);
            System.out.println("Now you are following " + user.getName());
        } else {
            System.out.println("You are already following " + user.getName());
        }
    }

    // Method for unfollow a user
    public void unfollowUser(User user) {
        if (followList.contains(user)) {
            followList.remove(user);
            System.out.println("you have stopped following " + user.getName());
        } else {
            System.out.println("you were no longer following " + user.getName());
        }
    }

    // Method for delete a user
    public void deleteUser( List<User> userList) {
        // Delete this user from followlist of another users
        for (User userL : userList) {
            if (userL.getFollowList().contains(this)) {
                this.unfollowUser(userL);
            }
        }
        //Delete Comments from user
        for (User user1 : userList) {
            List<Post> postlist = user1.getPostList();
            for (Post post : postlist) {
                List<Comment> commentList = post.getCommentList();
                List<Comment> commentsToRemove = new ArrayList<>();

                for (Comment comment : commentList) {
                    if (comment.getOwner().equals(this)) {
                        commentsToRemove.add(comment);
                    }
                }
                commentList.removeAll(commentsToRemove);
            }
        }
        //Delete Posts from user
        this.getPostList().clear();
        userList.remove(this);
        System.out.println("The user " + this.getName() + " has been eliminated.");
    }

    // Method for add a post
    public void addPost(Post post) {
        postList.add(post);
    }

    // Method for list all posts of a user
    public void listPosts() {
        if (this.postList.isEmpty()) {
            System.out.println("This user has no posts");
        } else {
            // Show post list of the user
            System.out.println("********************   Posts   *********************");
            for (Post post : this.postList) {
                if (post instanceof Text) {
                    // Text post
                    Text textPost = (Text) post;
                    textPost.showDetails();
                } else if (post instanceof Img) {
                    // Image post
                    Img imgPost = (Img) post;
                    imgPost.showDetails();
                } else if (post instanceof Video) {
                    // Video post
                    Video videoPost = (Video) post;
                    videoPost.showDetails();
                }
            }
        }
    }

    // Method to obtain comments from a user's list of posts
    public List<Comment> commentList() {
        List<Comment> comments = new ArrayList<>();
        for (Post post : postList) {
            comments.addAll(post.getCommentList());
        }
        return comments;
    }

    // Method for list all comments of a User
    public List<Comment> getUserCommentsList(List<User> users) {
        List<Comment> commentListUser = new ArrayList<>();
        for (User user1 : users) {
            List<Post> postlist = user1.getPostList();
            for (Post post : postlist) {
                List<Comment> commentList = post.getCommentList();
                for (Comment comment : commentList) {
                    if (comment.getOwner() == this) {
                        commentListUser.add(comment);

                    }
                }
            }
        }
        return commentListUser;
    }

    public void printCommentsFromUser(List<User> users) {
        List<Comment> commentList = getUserCommentsList(users);
        for (Comment comment : commentList) {
            System.out.println("- " + comment.getText() + "\n\t (Owner: " + comment.getOwner().getName() + ")\n\t" + comment.getDate());
        }
    }

    // Method for delete a Post
    public void deletePost(Post post) {
        if (postList.contains(post)) {
            postList.remove(post);
            System.out.println("The post has been deleted");
        }
    }

    public void showDetails() {
        System.out.println("\nName: " + this.name);
        // Show user list that follows
        System.out.println("users who are followed by this: ");
        for (User user : this.followList) {
            System.out.println("- " + user.getName());
        }
        listPosts();
    }

    public void showWall() {
        System.out.println("Wall of " + name);
        List<Post> wallPosts = new ArrayList<>();

        // Browse the list of followed users
        for (User followedUser : followList) {
            // Agrega los posts del usuario seguido al muro
            wallPosts.addAll(followedUser.getPostList());
        }

        // Sort posts in chronological order (most recent first)
        int n = wallPosts.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Post post1 = wallPosts.get(j);
                Post post2 = wallPosts.get(j + 1);

                // Compare the dates of the posts and if the first one is older, exchange them
                if (post1.getDate().before(post2.getDate())) {
                    Post temp = post1;
                    wallPosts.set(j, post2);
                    wallPosts.set(j + 1, temp);
                }
            }
        }

        // Show the first 10 posts on the wall
        int count = 0;
        for (Post post : wallPosts) {
            if (count >= 10) {
                break;
            }
            post.showDetails();
            count++;
        }
    }

    public List<User> suggestFriendsWithCommonFriends() {
        List<User> suggestedFriends = new ArrayList<>();
        List<User> userFriends = followList;

        //Browse the user's current friends
        for (User friend : userFriends) {
            // Gets the friends list of each current friend
            List<User> friendFriends = friend.getFollowList();

            // Compare each friend's friends with the original user's friends
            for (User potentialFriend : friendFriends) {
                if (!userFriends.contains(potentialFriend) && potentialFriend != this ) {
                    // Check if the potential friend is already on the suggestion list
                    boolean alreadyExists = false;
                    for (User suggestedFriend : suggestedFriends) {
                        if (suggestedFriend == potentialFriend) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    // If it doesn't exist in the suggestion list, add it
                    if (!alreadyExists) {
                        suggestedFriends.add(potentialFriend);
                    }
                }
            }
        }
        return suggestedFriends;
    }

    public void addPost(Date date) {
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
                this.addPost(new Text(date,content));
                break;
            case 2:
                title = Input.string("Enter the title of the image: ");
                dimensions = Input.string("Enter the dimensions of the image: ");
                this.addPost(new Img(date,title, dimensions));
                break;
            case 3:
                title = Input.string("Enter the title of the video: ");
                videoQuality = Input.string("Enter the video quality: ");
                duration = Input.integer("Enter the duration of the video (in seconds): ");
                this.addPost(new Video(date,title, videoQuality, duration));
                break;
            default:
                System.out.println("Invalid post type.");
                break;
        }
    }

}
