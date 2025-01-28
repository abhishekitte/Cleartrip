import Cleartrip.Models.Post;
import Cleartrip.Models.User;
import Cleartrip.Services.FeedService;
import Cleartrip.Services.PostService;
import Cleartrip.Services.UserService;
import exception.InvalidCommandException;
import exception.UserNotFoundException;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SocialMediaApp {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        FeedService feedService = new FeedService(postService);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter command:");
                String input = scanner.nextLine().trim();
                String[] command = input.split(" ", 3);

                if (command.length < 2) {
                    throw new InvalidCommandException("Invalid command syntax! Please try again.");
                }

                switch (command[0]) {
                    case "RegisterUser":
                        int userId = Integer.parseInt(command[1]);
                        String username = command[2];
                        System.out.println(userService.registerUser(userId, username));
                        break;

                    case "UploadPost":
                        userId = Integer.parseInt(command[1]);
                        String content = command[2];
                        userService.getUser(userId); // Validate user existence
                        System.out.println(postService.uploadPost(userId, content));
                        break;

                    case "InteractWithUser":
                        if (command.length != 4) {
                            throw new InvalidCommandException("Invalid InteractWithUser syntax! Use: FOLLOW/UNFOLLOW <userId1> <userId2>");
                        }
                        String interactionType = command[1];
                        int sourceId = Integer.parseInt(command[2]);
                        int targetId = Integer.parseInt(command[3]);

                        User sourceUser = userService.getUser(sourceId);
                        User targetUser = userService.getUser(targetId);

                        if (interactionType.equalsIgnoreCase("FOLLOW")) {
                            sourceUser.followUser(targetId);
                            System.out.println("Followed " + targetUser.getUsername() + "!!");
                        } else if (interactionType.equalsIgnoreCase("UNFOLLOW")) {
                            sourceUser.unfollowUser(targetId);
                            System.out.println("Unfollowed " + targetUser.getUsername() + "!!");
                        } else {
                            throw new InvalidCommandException("Unknown interaction type: " + interactionType);
                        }
                        break;

                    case "ShowFeed":
                        userId = Integer.parseInt(command[1]);
                        User user = userService.getUser(userId);

                        for (Post post : feedService.getFeedForUser(user)) {
                            User postUser = userService.getUser(post.getUserId());
                            System.out.println("UserName - " + postUser.getUsername());
                            System.out.println("Post - " + post.getContent());
                            System.out.println("Post time - " + post.getTimestamp());
                            System.out.println("Likes - " + post.getLikes() + ", Dislikes - " + post.getDislikes());
                        }
                        break;

                    default:
                        throw new InvalidCommandException("Unknown command: " + command[0]);
                }

            } catch (UserNotFoundException | InvalidCommandException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());


            }
        }
    }
}