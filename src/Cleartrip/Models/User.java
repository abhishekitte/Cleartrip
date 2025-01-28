package Cleartrip.Models;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final int userId;
    private final String username;
    private final Set<Integer> following;
    private final Set<Integer> followers;

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
        this.following = new HashSet<>();
        this.followers = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public Set<Integer> getFollowers() {
        return followers;
    }
    public void followUser(int userId){
        following.add(userId);
    }
    public void unfollowUser(int userId){
        following.remove(userId);
    }
}
