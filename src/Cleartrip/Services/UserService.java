package Cleartrip.Services;

import Cleartrip.Models.User;
import exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<Integer, User> users;
    public UserService(){
        this.users=new HashMap<>();
    }
    public String registerUser(int userId,String username){
        if(users.containsKey(userId)){
            throw new IllegalArgumentException( "User ID already exists!");
        }
        users.put(userId, new User(userId,username));
        return username+"Registered!";
    }
    public User getUser(int userId){
        User user=users.get(userId);
        if (user==null){
            throw new UserNotFoundException("User with ID"+ userId+"not found");
        }
        return user;
    }
}
