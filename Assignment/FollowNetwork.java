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
        users.addEdge(following, follower);
    }

    public boolean hasUser(String name)
    {
        return users.hasVertex(name);
    }

    public void removeUser(String name)
    {
        users.deleteNode(name);
    }

    public void displayNetworkList()
    {
        users.displayAsList();
    }

    private class UserData
    {
        String name;
        DSALinkedList posts;

        public UserData(String inName)
        {
            name = inName;
            posts = new DSALinkedList();
        }
    }
}
