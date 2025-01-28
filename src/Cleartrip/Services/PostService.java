package Cleartrip.Services;

import Cleartrip.Models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final List<Post> posts;
    private int postIdCounter;

    public PostService(){
        this.posts=new ArrayList<>();
        this.postIdCounter=1;
    }
    public String uploadPost(int userId, String content){
        Post post =new Post(postIdCounter++, userId, content);
        posts.add(post);
        return "Upload successful with post id: " +String.format("%03d",post.getPostId());
    }
    public List<Post> getPosts(){
        return posts;
    }
}
