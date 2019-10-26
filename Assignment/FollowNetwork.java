import java.util.*;

public class FollowNetwork
{
    private DSAGraph users;

    public FollowNetwork()
    {
        users = new DSAGraph();
    }

    public void addUser(String name)
    {
        UserData userData = new UserData(name);

        users.addVertex(name, userData);
    }

    public void addFollow(String following, String follower)
    {
        UserData user = null;
        user = (UserData)users.getNodeValue(follower);
        if(user != null)
        {
            user.addFollowing(following);
            users.setNodeValue(follower, user);
        }
        users.addEdge(following, follower);
    }

    public void removeFollow(String following, String follower)
    {
        users.removeEdge(following, follower);
    }

    public boolean hasUser(String name)
    {
        return users.hasVertex(name);
    }

    public void removeUser(String name)
    {
        users.deleteNode(name);
    }

    public void addPost(String name, String post)
    {
        UserData user = null;
        user = (UserData)users.getNodeValue(name);
        if(user != null)
        {
            user.addPost(post);
            users.setNodeValue(name, user);
        }
    }

    public void displayNetworkList()
    {
        users.displayAsList();
    }

    public void displayUser(String name)
    {
        UserData user = null;
        user = (UserData)users.getNodeValue(name);
        if(user != null)
        {
            System.out.println();
            System.out.println("------------------------------");
            System.out.println("Name: " + name);
            System.out.println("\tNo of Posts: " + user.posts.getCount());
            System.out.println("\tNo of Followers: " + (users.getAdjacent(name)).getCount());
            System.out.println("\tNo of Following: " + user.following.getCount());
            System.out.println("------------------------------");
        }
        else
        {
            System.out.println("User not found");
        }
    }

    private class UserData
    {
        private String name;
        private DSALinkedList following;
        private DSALinkedList posts;

        public UserData(String inName)
        {
            name = inName;
            following = new DSALinkedList();
            posts = new DSALinkedList();
        }

        public void addFollowing(String name)
        {
            posts.insertLast(name);
        }

        public void addPost(String post)
        {
            posts.insertLast(post);
        }
    }

    private class Post
    {
        private String post;
        private int likeNum;

        public Post(String inPost)
        {
            post = inPost;
            likeNum = 0;
        }

    }
}
