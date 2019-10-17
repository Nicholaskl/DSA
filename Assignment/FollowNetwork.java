public class FollowNetwork
{
    DSALinkedList users;    

    public FollowNetwork()
    {
        DSALinkedList users = new DSALinkedList(); 
    }

    public void newUser(String name)
    {
        UserData user = new UserData();

        userFollowers.addVertex(name);
        users.insertLast(userFollowers);
    }

    private class UserData
    {
        String name;
        DSAGraph followers;
        DSALinkedList posts;
        
        public UserData()
        {
            a
        }
    }
}
