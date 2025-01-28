package Cleartrip.Services;

import Cleartrip.Models.Post;
import Cleartrip.Models.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FeedService {
    private final PostService postService;
    public FeedService(PostService postService){
        this.postService=postService;
    }
    public List<Post> getFeedForUser(User user){
        List<Post> followedPosts =new ArrayList<>();
        List<Post> nonFollowedPosts=new ArrayList<>();

        for(Post post: postService.getPosts()){
            if(user.getFollowing().contains(post.getUserId())){
                followedPosts.add(post);
            }else{
                nonFollowedPosts.add(post);
            }
        }
        followedPosts.sort(Comparator.comparing(Post::getTimestamp).reversed());
        nonFollowedPosts.sort(Comparator.comparing(Post::getTimestamp).reversed());

        followedPosts.addAll(nonFollowedPosts);
        return followedPosts;
    }

}
