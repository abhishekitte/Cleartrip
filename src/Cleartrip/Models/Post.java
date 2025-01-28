package Cleartrip.Models;

import java.time.LocalDateTime;

public class Post {
    private final int postId;
    private final int userId;
    private final String content;
    private final LocalDateTime timestamp;
    private int likes;
    private int dislikes;

    public Post(int postId, int userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timestamp=LocalDateTime.now();
        this.likes=0;
        this.dislikes=0;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
    public void likePost(){
        likes++;
    }
    public void dislikePost(){
        dislikes++;
    }
}
