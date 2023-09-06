package com.campusdual;

import java.util.*;

public class Initializer {
    // Create a users list
    private List<User> userList = new ArrayList<>();

    // Function to generate a random date before the current date
    private static Date randomDateGenerator() {
        // Create a Random object to generate random dates
        Random random = new Random();
        // Obtains de actual date
        Calendar calendar = Calendar.getInstance();
        Date actualDate = calendar.getTime();
        // Generate a random num to decrement de actual date
        int daysfordward = random.nextInt(365) + 1;
        // Calcs to decrement de actual date and add to post
        calendar.setTime(actualDate);
        calendar.add(Calendar.DAY_OF_YEAR, -daysfordward);
        return calendar.getTime();
    }

    public void setUserList() {

        String[] names = {
                "Juan Pérez",
                "María Rodríguez",
                "Carlos González",
                "Ana López",
                "Pedro García",
                "Luisa Martínez",
                "José Ramírez",
                "Laura Fernández",
                "Miguel Torres",
                "Isabel Sánchez",
                "Andrés Martín",
                "Elena Jiménez",
                "Javier Ruiz",
                "Carmen Moreno",
                "Diego Herrera",
                "Sofía Castro",
                "Ricardo Vargas",
                "Patricia Silva",
                "Fernando Ortiz",
                "Raquel Ramos"
        };

        for (String name : names) {
            this.userList.add(new User(name));
        }
        for (User user : userList) {
            setPostsList(user);
            setRandomFollowingUsers(user);
        }
    }
    public List<User> getUserList() {
        return userList;
    }

    public void setPostsList(User user) {
        // Create a number of posts
        for (int i = 1; i <= 20; i++) {
            Date date = randomDateGenerator();

            // Alternate a tipe of posts
            if (i % 3 == 0) {
                String content = "Content of post #" + i;
                Text textPost = new Text(date, content);
                user.addPost(textPost);
                setCommentsList(textPost);
            } else if (i % 3 == 1) {
                String imageTitle = "Title of the image #" + i;
                String dimensions = "800x600";
                Img imagePost = new Img(date, imageTitle, dimensions);
                user.addPost(imagePost);
                setCommentsList(imagePost);
            } else {
                String videotitle = "Title of the video #" + i;
                String videoQuality = "HD";
                int videoDuration = 120;
                Video videoPost = new Video(date, videotitle, videoQuality, videoDuration);
                user.addPost(videoPost);
                setCommentsList(videoPost);
            }
        }
    }

    public void setCommentsList(Post post) {
        Random random = new Random();
        int numberOfComments = random.nextInt(5) + 1;
        for (int i = 0; i < numberOfComments; i++) {
            Date commentDate = randomDateGeneratorForComments(post.getDate());
            String commentText = "Comment #" + i + " in " + post.getClass().getSimpleName();
            User commentOwner = userList.get(random.nextInt(userList.size()));
            Comment comment = new Comment(commentText, commentDate, commentOwner);
            post.addComment(comment);
        }
    }

    // Function to generate a random date after the date of a post
    private Date randomDateGeneratorForComments(Date postDate) {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(postDate);
        int dayForward = random.nextInt(365) + 1;
        calendar.add(Calendar.DAY_OF_YEAR, dayForward);
        return calendar.getTime();
    }

    public void setRandomFollowingUsers(User user) {
        Random random = new Random();
        // Generate a random number of users to follow (between 0 and userList.size() - 1)
        int numFollowing = random.nextInt(userList.size());
        // Make sure the user doesn't follow themselves
        List<User> usersToFollow = new ArrayList<>(userList);
        usersToFollow.remove(user);
        // shuffle users to follow
        Collections.shuffle(usersToFollow);
        // Add users to list
        for (int i = 0; i < numFollowing; i++) {
            user.getFollowList().add(usersToFollow.get(i));
        }
    }

}
