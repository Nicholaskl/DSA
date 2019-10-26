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

    public void printPost()
    {
        DSALinkedList userList = users.getVertices();
        DSALinkedList posts;
        Iterator iter = userList.iterator();
        Iterator iterP;
        UserData curr;
        PostClass currPost;

        while (iter.hasNext())
        {
            curr = (UserData)iter.next();
            System.out.println("uname:" + curr.name);
            posts = curr.posts;
            iterP = posts.iterator();
            while (iter.hasNext())
            {
                currPost = (PostClass)iter.next();
                System.out.println("post:" + currPost.post);
            }
        }
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

    public void orderByFollower()
    {
        DSALinkedList userList = users.getVertices();
        DSALinkedList sortedList = new DSALinkedList();
        DSALinkedList tempList = new DSALinkedList();
        UserData curr, sortedFirst, sortedLast;
        Iterator iter = userList.iterator();

        while (iter.hasNext())
        {
            curr = (UserData)iter.next();

            if(sortedList.isEmpty())
            {
                sortedList.insertFirst(curr);
            }
            else
            {
                sortedFirst = (UserData)sortedList.peekFirst();
                sortedLast = (UserData)sortedList.peekLast();

                if ((users.getAdjacent(curr.name).getCount()) > (users.getAdjacent(sortedLast.name).getCount()))
                {
                    sortedList.insertLast(curr);
                }
                else if ((users.getAdjacent(curr.name).getCount()) < (users.getAdjacent(sortedFirst.name).getCount()))
                {
                    sortedList.insertFirst(curr);
                }
                else
                {
                    while ((users.getAdjacent(curr.name).getCount()) < (users.getAdjacent(sortedLast.name).getCount()))
                    {
                        tempList.insertFirst(sortedList.removeLast());
                        sortedLast = (UserData)sortedList.peekLast();
                    }
                    sortedList.insertLast(curr);
                    while(!tempList.isEmpty())
                    {
                        sortedList.insertLast(tempList.removeFirst());
                    }
                }
            }
        }

        userList = new DSALinkedList();
        iter = sortedList.iterator();
        while (iter.hasNext())
        {
            curr = (UserData)iter.next();
            userList.insertFirst(curr);
        }

        System.out.println();
        System.out.println("------------------------------");
        iter = userList.iterator();
        while (iter.hasNext())
        {
            curr = (UserData)iter.next();
            System.out.println("\t" + users.getAdjacent(curr.name).getCount() + " followers: " + curr.name);
        }
    }

    public void orderByLike()
    {
        DSALinkedList userList = users.getVertices();
        DSALinkedList postlist =  new DSALinkedList();
        DSALinkedList usersPosts =  new DSALinkedList();
        DSALinkedList sortedList = new DSALinkedList();
        DSALinkedList tempList = new DSALinkedList();
        PostClass currP, sortedFirst, sortedLast;
        UserData curr;
        Iterator iter = userList.iterator();
        Iterator postIt;

        while (iter.hasNext())
        {
            curr = (UserData)iter.next();
            usersPosts = curr.posts;
            postIt = usersPosts.iterator();
            while (postIt.hasNext())
            {
                currP = (PostClass)postIt.next();
                postlist.insertLast(currP);
            }
        }

        if(postlist.isEmpty())
        {
            System.out.println("No Posts");
        }
        else
        {
            iter = postlist.iterator();
            while (iter.hasNext())
            {
                currP = (PostClass)iter.next();

                if(sortedList.isEmpty())
                {
                    sortedList.insertFirst(currP);
                }
                else
                {
                    sortedFirst = (PostClass)sortedList.peekFirst();
                    sortedLast = (PostClass)sortedList.peekLast();

                    if ((currP.likeNum) > (sortedLast.likeNum))
                    {
                        sortedList.insertLast(currP);
                    }
                    else if ((currP.likeNum) < (sortedFirst.likeNum))
                    {
                        sortedList.insertFirst(currP);
                    }
                    else
                    {
                        while ((currP.likeNum) < (sortedLast.likeNum))
                        {
                            tempList.insertFirst(sortedList.removeLast());
                            sortedLast = (PostClass)sortedList.peekLast();
                        }
                        sortedList.insertLast(currP);
                        while(!tempList.isEmpty())
                        {
                            sortedList.insertLast(tempList.removeFirst());
                        }
                    }
                }
            }

            postlist = new DSALinkedList();
            iter = sortedList.iterator();
            while (iter.hasNext())
            {
                currP = (PostClass)iter.next();
                postlist.insertFirst(currP);
            }

            System.out.println();
            System.out.println("------------------------------");
            iter = postlist.iterator();
            while (iter.hasNext())
            {
                currP = (PostClass)iter.next();
                System.out.println("  " + currP.likeNum + " likes|" + currP.poster + "|" + currP.post);
            }
        }
    }

    private class UserData
    {
        private String name;
        private DSALinkedList following;
        private DSALinkedList posts;

        public UserData()
        {
            name = "";
            following = new DSALinkedList();
            posts = new DSALinkedList();
        }

        public UserData(String inName)
        {
            name = inName;
            following = new DSALinkedList();
            posts = new DSALinkedList();
        }

        public void addFollowing(String name)
        {
            following.insertLast(name);
        }

        public void addPost(String inPost)
        {
            PostClass post = new PostClass(inPost, name);
            posts.insertLast(post);
        }
    }

    private class PostClass
    {
        private String post;
        private String poster;
        private int likeNum;

        public PostClass(String inPost, String inPoster)
        {
            post = inPost;
            poster = inPoster;
            likeNum = 0;
        }
    }
}
