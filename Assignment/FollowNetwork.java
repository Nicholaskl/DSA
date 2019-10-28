import java.util.*;

public class FollowNetwork
{
    private DSAGraph users;
    private DSALinkedList posts;

    public FollowNetwork()
    {
        users = new DSAGraph();
        posts = new DSALinkedList();
    }

    public String displayPosts()
    {
        String output = "";
        PostClass curr;
        Iterator iter = posts.iterator();
        Iterator iter2;

        if(posts.isEmpty())
        {
            System.out.println("No posts");
        }
        else
        {
            output += "\n";
            output += "------------------------------" + "\n";

            while (iter.hasNext())
            {
                curr = (PostClass)iter.next();
                output += "  Post: " + curr.post + "\n";
                output += "  Poster: " + curr.poster + "\n";
                output += "  Liked By: ";
                if(curr.likedBy.getCount() == 1)
                {
                    output += curr.likedBy.peekFirst();
                }
                else
                {
                    iter2 = curr.likedBy.iterator();
                    while(iter2.hasNext())
                    {
                        output += iter2.next() + ", ";
                    }
                }
                output += "\n";
                output += "  Time: " + curr.time + "\n";
                output += "\n";
            }

        }
        return output;
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

    public void addPost(String inPost, String name)
    {
        if(users.hasVertex(name))
        {
            PostClass post = new PostClass(inPost, name);
            posts.insertLast(post);
        }
        else
        {
            System.out.println(name + " not Found!");
        }
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
            System.out.println("\tNo of Posts: " + posts.getCount());
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
        DSALinkedList usersPosts =  new DSALinkedList();
        DSALinkedList sortedList = new DSALinkedList();
        DSALinkedList tempList = new DSALinkedList();
        PostClass currP, sortedFirst, sortedLast;
        UserData curr;
        Iterator iter;

        if(posts.isEmpty())
        {
            System.out.println("No Posts");
        }
        else
        {
            iter = posts.iterator();
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

            posts = new DSALinkedList();
            iter = sortedList.iterator();
            while (iter.hasNext())
            {
                currP = (PostClass)iter.next();
                posts.insertFirst(currP);
            }

            System.out.println();
            System.out.println("------------------------------");
            iter = posts.iterator();
            while (iter.hasNext())
            {
                currP = (PostClass)iter.next();
                System.out.println("  " + currP.likeNum + " likes|" + currP.poster + "|" + currP.post);
            }
        }
    }

    public String outputFile()
    {
        String output = "";
        DSALinkedList usersList = users.getVertices();
        DSALinkedList followersList;
        UserData currUser;
        Iterator iter = usersList.iterator();
        Iterator iterFollowers;
        String follower;
        while (iter.hasNext())
        {
            currUser = (UserData)iter.next();
            output += currUser.name + "\n";
        }

        iter = usersList.iterator();
        while (iter.hasNext())
        {
            currUser = (UserData)iter.next();
            followersList = users.getAdjacent(currUser.name);
            iterFollowers = followersList.iterator();
            while (iterFollowers.hasNext())
            {
                follower = (String)iterFollowers.next();
                output += currUser.name + ":" + follower + "\n";
            }
        }

        return output;
    }

    public void timeStep(double likeProb, double followProb)
    {
        DSALinkedList adjacentVertices, showPost, checkNext;
        Iterator iter = posts.iterator();
        Iterator iterAdj, iterShow;
        PostClass curr;
        String currFollower;
        double currLikeP, currFollowP;

        checkNext = new DSALinkedList();
        showPost = new DSALinkedList();

        while (iter.hasNext())
        {
            curr = (PostClass)iter.next();
            for(int ii=0; ii<= curr.time; ii++)
            {
                if(ii==0)
                {
                    checkNext = new DSALinkedList();
                    showPost = new DSALinkedList();
                    adjacentVertices = users.getAdjacent(curr.poster);
                    iterAdj = adjacentVertices.iterator();
                    while(iterAdj.hasNext())
                    {
                        currFollower = (String)iterAdj.next();
                        if(curr.hasLiked(currFollower))
                        {
                            checkNext.insertLast(currFollower);
                        }
                        else if(!curr.hasSeen(currFollower))
                        {
                            currLikeP = Math.random();
                            curr.setSeen(currFollower);
                            if(currLikeP <= likeProb)
                            {
                                curr.setLike(currFollower);
                            }
                        }
                    }
                }
                else
                {
                    showPost = checkNext;
                    checkNext = new DSALinkedList();
                    iterShow = showPost.iterator();
                    while(iterShow.hasNext())
                    {
                        adjacentVertices = users.getAdjacent((String)iterShow.next());
                        iterAdj = adjacentVertices.iterator();
                        while(iterAdj.hasNext())
                        {
                            currFollower = (String)iterAdj.next();
                            if(curr.hasLiked(currFollower))
                            {
                                checkNext.insertLast(currFollower);
                            }
                            else if(!curr.hasSeen(currFollower))
                            {
                                currLikeP = Math.random();
                                if(currLikeP <= likeProb)
                                {
                                    curr.setLike(currFollower);
                                    currFollowP = Math.random();
                                    if(currFollowP <= followProb)
                                    {
                                        addFollow(curr.poster, currFollower);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            curr.time++;
        }
    }

    private class UserData
    {
        private String name;
        private DSALinkedList following;

        public UserData()
        {
            name = "";
            following = new DSALinkedList();
        }

        public UserData(String inName)
        {
            name = inName;
            following = new DSALinkedList();
        }

        public void addFollowing(String name)
        {
            following.insertLast(name);
        }

    }

    private class PostClass
    {
        private String post;
        private String poster;
        private DSALinkedList likedBy;
        private DSALinkedList seenBy;
        private int likeNum;
        private int time;

        public PostClass(String inPost, String inPoster)
        {
            post = inPost;
            poster = inPoster;
            likedBy = new DSALinkedList();
            seenBy = new DSALinkedList();
            likeNum = 0;
            time = 0;
        }

        public boolean hasLiked(String user)
        {
            Boolean liked = false;
            String currUser;

            Iterator iter = likedBy.iterator();
            while (iter.hasNext())
            {
                currUser = (String)iter.next();
                if(currUser.equals(user))
                {
                    liked = true;
                }
            }
            return liked;
        }

        public void setSeen(String user)
        {
            String currUser;
            boolean seen = false;

            Iterator iter = seenBy.iterator();
            while (iter.hasNext())
            {
                currUser = (String)iter.next();
                if(currUser.equals(user))
                {
                    seen = true;
                }
            }

            if(seen)
            {
                System.out.println("User already seen");
            }
            else
            {
                seenBy.insertLast(user);
            }
        }

        public void setLike(String user)
        {
            String currUser;
            boolean liked = false;

            Iterator iter = likedBy.iterator();
            while (iter.hasNext())
            {
                currUser = (String)iter.next();
                if(currUser.equals(user))
                {
                    liked = true;
                }
            }
            if(liked)
            {
                System.out.println(user + " already liked");
            }
            else
            {
                likedBy.insertLast(user);
            }
        }

        public boolean hasSeen(String user)
        {
            Boolean seen = false;
            String currUser;

            Iterator iter = seenBy.iterator();
            while (iter.hasNext())
            {
                currUser = (String)iter.next();
                if(currUser.equals(user))
                {
                    seen = true;
                }
            }
            return seen;
        }
    }
}
