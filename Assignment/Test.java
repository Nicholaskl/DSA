/*
 * File: Test.java
 * File Created: Thursday, 5th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Test file for the Follow Network Object
 */
import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
            FollowNetwork network = new FollowNetwork(); //Creates the object (Network)

            System.out.println(".....................Follow Network Tests.....................");
            System.out.println("-----Testing Add/Remove User and Find-----");
            System.out.print("User1 is added to the network. should have 0 likes, posts and followers");
            System.out.println("Also adding User2, User3, User4, User5");
            network.addUser("User1");
            network.displayUser("User1");
            network.addUser("User2");
            network.addUser("User3");
            network.addUser("User4");
            network.addUser("User5");
            System.out.print("User5 is added to the network. should have 0 likes, posts and followers");
            network.displayUser("User5");
            System.out.println("Removing User5 from network");
            network.removeUser("User5");
            System.out.println("Should no longer see User5");
            network.displayNetworkList();

            System.out.println("-----Testing Add Follow and Print Network-----");
            System.out.println("User 4 will follow User 1");
            network.addFollow("User1", "User4");
            System.out.println("User 4 will follow User 2");
            network.addFollow("User2", "User4");
            System.out.println("User 4 will follow User 3");
            network.addFollow("User3", "User4");
            System.out.println("User 1 will follow User 3");
            network.addFollow("User3", "User1");
            System.out.println("User 2 will follow User 3");
            network.addFollow("User3", "User2");
            System.out.println();
            System.out.println("Display network, should see those follows");
            network.displayNetworkList();
            System.out.println();
            System.out.println("User2 will remove follow from User 3");
            network.removeFollow("User3", "User2");
            System.out.println("Display network, should see user2 doesn't follow User 3 anymore");
            network.displayNetworkList();

            System.out.println("-----Testing Add Post and print posts-----");
            System.out.println("User 4 Posts Hello World");
            network.addPost("Hello world", "User4");
            System.out.println("User 2 Posts DSA Assignment");
            network.addPost("DSA Assignment", "User2");
            System.out.println("Printing the posts, should see all information. time should be 0");
            System.out.println(network.displayPosts());

            System.out.println("-----Testing TimeStep-----");
            System.out.println("Testing a timestep on the posts with probability of like = 0.99 and probability of follow = 0.8");
            network.timeStep(0.99, 0.8);
            System.out.println("Displaying posts. time should increase by 1 on the posts");
            System.out.println("Should also see changes with likes");
            System.out.print(network.displayPosts());
            System.out.println("User 3 Posts skekUng");
            network.addPost("skekUng", "User3");
            System.out.println("Testing a timestep on the posts with probability of like = 0.99 and probability of follow = 0.8");
            network.timeStep(0.99, 0.8);
            System.out.println("Displaying posts. time should increase by 1 on the posts");
            System.out.println("Should also see changes with likes");
            System.out.print(network.displayPosts());


            System.out.println("-----Testing Ordered Displays-----");
            System.out.println("Testing display by amount of followers");
            System.out.println("Output should be user3, user2, user1, user4");
            network.orderByFollower();
            System.out.println("Testing display of posts by amount of likes");
            network.orderByLike();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
