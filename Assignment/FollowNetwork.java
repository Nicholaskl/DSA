/*
 * File: FollowNetwork.java
 * File Created: Sunday, 27th October 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Wednesday, 30th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: Contains the main object class for the Social Network
 */
import java.util.*;

public class FollowNetwork
{
    private DSAGraph users;
    private DSALinkedList posts;

    /*
    * SUBMODULE: FollowNetwork
    * IMPORT:
    * EXPORT:
    * ASSERTION: default constructor for FollowNetwork class
    */
    public FollowNetwork()
    {
        users = new DSAGraph();
        posts = new DSALinkedList();
    }

    /*
    * SUBMODULE: displayPosts
    * IMPORT:
    * EXPORT: output(String)
    * ASSERTION: returns a string with all of the posts in it formatted
    */
    public String displayPosts()
    {
        String output = "";
        PostClass curr;
        Iterator iter = posts.iterator();
        Iterator iter2;

        if(posts.isEmpty()) //can't print if no posts
        {
            System.out.println("No posts");
        }
        else
        {
            output += "\n";
            output += "------------------------------" + "\n";

            while (iter.hasNext()) //traverse posts list
            {
                curr = (PostClass)iter.next();
                output += "  Post: " + curr.post + "\n";
                output += "  Poster: " + curr.poster + "\n";
                output += "  Seen by: " + curr.seenBy.getCount() + "\n";
                output += "  Liked By: ";
                if(curr.likedBy.getCount() == 1) //checks if liked by one user
                {
                    output += curr.likedBy.peekFirst(); //check first
                }
                else
                {
                    iter2 = curr.likedBy.iterator(); //get users that have liked the post
                    while(iter2.hasNext()) //traverse users that have liked the post
                    {
                        output += iter2.next() + ", "; //output them
                    }
                }
                output += "\n";
                output += "  Time: " + curr.time + "\n";
                output += "\n";
            }

        }
        return output;
    }

    /*
    * SUBMODULE: addUser
    * IMPORT: name(String)
    * EXPORT:
    * ASSERTION: Adds a user to the Social network
    */
    public void addUser(String name)
    {
        UserData userData = new UserData(name);

        users.addVertex(name, userData);
    }

    /*
    * SUBMODULE: addFollow
    * IMPORT: following(String), follower(String)
    * EXPORT:
    * ASSERTION: adds a follow from the follower to the person they will be following
    */
    public void addFollow(String following, String follower)
    {
        UserData user = null;
        user = (UserData)users.getNodeValue(follower);
        if(user != null) //if user not found
        {
            user.addFollowing(following);
            users.setNodeValue(follower, user);
        }
        users.addEdge(following, follower);
    }

    /*
    * SUBMODULE: addPost
    * IMPORT: inPost(String), name(String)
    * EXPORT:
    * ASSERTION: Adds a post to the network under a user and a message
    */
    public void addPost(String inPost, String name)
    {
        if(users.hasVertex(name)) //if user exists, add a post
        {
            PostClass post = new PostClass(inPost, name);
            posts.insertLast(post);
        }
        else
        {
            System.out.println(name + " not Found!");
        }
    }

    /*
    * SUBMODULE: removeFollow
    * IMPORT: following(String), follower(String)
    * EXPORT:
    * ASSERTION: removes a follow from between to users
    */
    public void removeFollow(String following, String follower)
    {
        users.removeEdge(following, follower);
    }

    /*
    * SUBMODULE: hasUser
    * IMPORT:
    * EXPORT: hasUser(boolean)
    * ASSERTION: returns whether the network has a user
    */
    public boolean hasUser(String name)
    {
        return users.hasVertex(name);
    }

    /*
    * SUBMODULE: removeUser
    * IMPORT: name(String)
    * EXPORT:
    * ASSERTION: removes a user from the network
    */
    public void removeUser(String name)
    {
        users.deleteNode(name);
    }

    /*
    * SUBMODULE: displayNetworkList
    * IMPORT:
    * EXPORT:
    * ASSERTION: Calls the graph to print the adjacency list
    */
    public void displayNetworkList()
    {
        users.displayAsList();
    }

    /*
    * SUBMODULE: displayUser
    * IMPORT: name(String)
    * EXPORT:
    * ASSERTION: Displays a users details
    */
    public void displayUser(String name)
    {
        UserData user = null;
        user = (UserData)users.getNodeValue(name);
        if(user != null) //if user does exist
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

    /*
    * SUBMODULE: orderByFollower
    * IMPORT:
    * EXPORT:
    * ASSERTION: Prints out the users in order of number of followers
    */
    public void orderByFollower()
    {
        DSALinkedList userList = users.getVertices();
        DSALinkedList sortedList = new DSALinkedList();
        DSALinkedList tempList = new DSALinkedList();
        UserData curr, sortedFirst, sortedLast;
        Iterator iter = userList.iterator();

        while (iter.hasNext()) //traverse users
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
                { //if curr users followers are bigger than the end of the sorted list
                    sortedList.insertLast(curr); //add curr to the end
                }
                else if ((users.getAdjacent(curr.name).getCount()) < (users.getAdjacent(sortedFirst.name).getCount()))
                { //if curr users followers are smaller than the beginning of the sorted list
                    sortedList.insertFirst(curr); //add curr to the beginning
                }
                else
                {
                    while ((users.getAdjacent(curr.name).getCount()) < (users.getAdjacent(sortedLast.name).getCount()))
                    { //while curr is smaller than the last value of the sorted list
                        tempList.insertFirst(sortedList.removeLast()); //remove the last of the sorted list
                        sortedLast = (UserData)sortedList.peekLast(); //set last to new last of the list
                    }
                    sortedList.insertLast(curr); //now insert value
                    while(!tempList.isEmpty()) //insert the rest of the sorted list back
                    {
                        sortedList.insertLast(tempList.removeFirst());
                    }
                }
            }
        }

        userList = new DSALinkedList();
        iter = sortedList.iterator();
        while (iter.hasNext()) //traverses sorted list
        {
            curr = (UserData)iter.next();
            userList.insertFirst(curr); //adds to user list (from largest to smallest)
        }

        System.out.println();
        System.out.println("------------------------------");
        iter = userList.iterator();
        while (iter.hasNext()) //traverses users that have now been sorted
        {
            curr = (UserData)iter.next();
            System.out.println("\t" + users.getAdjacent(curr.name).getCount() + " followers: " + curr.name);
        } //prints data
    }

    /*
    * SUBMODULE: orderByLike
    * IMPORT:
    * EXPORT:
    * ASSERTION: Prints out the posts in order of number of likes
    */
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
            while (iter.hasNext()) //traverse posts
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
                    { //if curr is bigger than last of the sorted list
                        sortedList.insertLast(currP); //add it to the end
                    }
                    else if ((currP.likeNum) < (sortedFirst.likeNum))
                    { //if curr is smalle than the front of the sorted list
                        sortedList.insertFirst(currP); //add it to the front
                    }
                    else
                    {
                        while ((currP.likeNum) < (sortedLast.likeNum))
                        { //while curr is smaller than the last value of sorted list
                            tempList.insertFirst(sortedList.removeLast()); //remove last of sorted list
                            sortedLast = (PostClass)sortedList.peekLast(); //set last to last of list
                        }
                        sortedList.insertLast(currP); //insert curr to end
                        while(!tempList.isEmpty()) //add back end part of sorted list back on
                        {
                            sortedList.insertLast(tempList.removeFirst());
                        }
                    }
                }
            }

            posts = new DSALinkedList();
            iter = sortedList.iterator();
            while (iter.hasNext()) //traverse sorted list
            {
                currP = (PostClass)iter.next();
                posts.insertFirst(currP); //inserts back into posts (reverse order)
            }

            System.out.println();
            System.out.println("------------------------------");
            iter = posts.iterator();
            while (iter.hasNext()) //traverse posts
            {
                currP = (PostClass)iter.next();
                System.out.println("  " + currP.likeNum + " likes|" + currP.poster + "|" + currP.post);
            } //print post data
        }
    }

    /*
    * SUBMODULE: outputFile
    * IMPORT:
    * EXPORT: output(String)
    * ASSERTION: Outputs a string of the network to be printed
    */
    public String outputFile()
    {
        String output = "";
        DSALinkedList usersList = users.getVertices();
        DSALinkedList followersList;
        UserData currUser;
        Iterator iter = usersList.iterator();
        Iterator iterFollowers;
        String follower;

        while (iter.hasNext()) //traverse users
        {
            currUser = (UserData)iter.next();
            output += currUser.name + "\n"; //add users to top of file
        }

        iter = usersList.iterator();
        while (iter.hasNext()) //traverse users
        {
            currUser = (UserData)iter.next();
            followersList = users.getAdjacent(currUser.name);
            iterFollowers = followersList.iterator();
            while (iterFollowers.hasNext()) //traverse users followers
            {
                follower = (String)iterFollowers.next();
                output += currUser.name + ":" + follower + "\n"; //add follow relationships to file
            }
        }

        return output;
    }

    /*
    * SUBMODULE: timeStep
    * IMPORT: likeProb(double), followProb(double)
    * EXPORT:
    * ASSERTION: A graph propogation through posts and people
    */
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

        while (iter.hasNext()) //traverse post list
        {
            curr = (PostClass)iter.next();
            for(int ii=0; ii<= curr.time; ii++) //loop for as long as the time of the post (to get to same value)
            {
                if(ii==0) //if first timestep for the post
                {
                    checkNext = new DSALinkedList();
                    showPost = new DSALinkedList();
                    adjacentVertices = users.getAdjacent(curr.poster);
                    iterAdj = adjacentVertices.iterator();
                    while(iterAdj.hasNext()) //traverse followers of the poster
                    {
                        currFollower = (String)iterAdj.next();
                        if(curr.hasLiked(currFollower)) //if the follower has liked it
                        {
                            checkNext.insertLast(currFollower); //add it to the check next list
                        }
                        else if(!curr.hasSeen(currFollower)) //otherwise they haven't
                        {
                            currLikeP = Math.random();
                            curr.setSeen(currFollower);
                            if(currLikeP <= likeProb) //they have a probability of liking the post
                            {
                                curr.setLike(currFollower); //if the probability falls in place, they like it
                            }
                        }
                    }
                }
                else //otherwise not first timestep for the post
                {
                    showPost = checkNext; //showPost is this iteration of showing the post too
                    checkNext = new DSALinkedList(); //reset the next iteration of people
                    iterShow = showPost.iterator();
                    while(iterShow.hasNext()) //iterate through people to show post too
                    {
                        adjacentVertices = users.getAdjacent((String)iterShow.next());
                        iterAdj = adjacentVertices.iterator();
                        while(iterAdj.hasNext()) //iterate through their followers
                        {
                            currFollower = (String)iterAdj.next();
                            if(curr.hasLiked(currFollower)) //if they have liked the post
                            {
                                checkNext.insertLast(currFollower); //make their followers see the post
                            }
                            else if(!curr.hasSeen(currFollower)) //otherwise they haven't seen it
                            {
                                currLikeP = Math.random();
                                if(currLikeP <= likeProb) //chance of liking the post
                                {
                                    curr.setSeen(currFollower);
                                    curr.setLike(currFollower); //if happens, like the post
                                    currFollowP = Math.random();
                                    if(currFollowP <= followProb) //chance of following
                                    {
                                        addFollow(curr.poster, currFollower); //if happens follow the poster
                                    }
                                }
                            }
                        }
                    }
                }
            }
            curr.time++; //time is increased for that post
        }
    }

    private class UserData
    {
        private String name;
        private DSALinkedList following;

        /*
        * SUBMODULE: UserData
        * ASSERTION: Defualt constructor for the UserData object
        */
        public UserData()
        {
            name = "";
            following = new DSALinkedList();
        }

        /*
        * SUBMODULE: UserData
        * IMPORT: inName(String)
        * ASSERTION: Alternate constructor for the User Data object
        */
        public UserData(String inName)
        {
            name = inName;
            following = new DSALinkedList();
        }

        /*
        * SUBMODULE: addFollowwing
        * IMPORT: name(String)
        * EXPORT: void
        * ASSERTION: Adds the name to the following list of the user
        */
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

        /*
        * SUBMODULE: PostClass
        * IMPORT: inPost(String), inPoster(String)
        * ASSERTION: Alternate constructor for the PostClass object
        */
        public PostClass(String inPost, String inPoster)
        {
            post = inPost;
            poster = inPoster;
            likedBy = new DSALinkedList();
            seenBy = new DSALinkedList();
            likeNum = 0;
            time = 0;
        }

        /*
        * SUBMODULE: hasLiked
        * IMPORT: user(String)
        * EXPORT: liked(Boolean)
        * ASSERTION: Returns whether the user has liked the post
        */
        public boolean hasLiked(String user)
        {
            Boolean liked = false;
            String currUser;

            Iterator iter = likedBy.iterator();
            while (iter.hasNext()) //iterate through likedBy list
            {
                currUser = (String)iter.next();
                if(currUser.equals(user)) //if the user has liked the post, return true
                {
                    liked = true;
                }
            }
            return liked;
        }

        /*
        * SUBMODULE: setSeen
        * IMPORT: user(String)
        * EXPORT: void
        * ASSERTION: Adds user to the list if they have not seen the post yet
        */
        public void setSeen(String user)
        {
            String currUser;
            boolean seen = false;

            Iterator iter = seenBy.iterator();
            while (iter.hasNext()) //iterate through the seen list
            {
                currUser = (String)iter.next();
                if(currUser.equals(user)) //if they have seen the post set to true
                {
                    seen = true;
                }
            }

            if(seen)
            {
                System.out.println("User already seen");
            }
            else //if they haven't seen, add them to the seen list
            {
                seenBy.insertLast(user);
            }
        }

        /*
        * SUBMODULE: setLike
        * IMPORT: user(String)
        * EXPORT: void
        * ASSERTION: Adds user to the liked list if they haven't liked it already
        */
        public void setLike(String user)
        {
            String currUser;
            boolean liked = false;

            Iterator iter = likedBy.iterator();
            while (iter.hasNext()) //iterate through liked list
            {
                currUser = (String)iter.next();
                if(currUser.equals(user)) //if they have liked the post, return true
                {
                    liked = true;
                }
            }
            if(liked)
            {
                System.out.println(user + " already liked");
            }
            else //if they haven't liked the post
            {
                likedBy.insertLast(user); //add them to the liked list
                likeNum++;
            }
        }

        /*
        * SUBMODULE: hasSeen
        * IMPORT: user(String)
        * EXPORT: seen(Boolean)
        * ASSERTION: Returns whether the user has seen the post
        */
        public boolean hasSeen(String user)
        {
            Boolean seen = false;
            String currUser;

            Iterator iter = seenBy.iterator();
            while (iter.hasNext()) //iterate through seen list
            {
                currUser = (String)iter.next();
                if(currUser.equals(user)) //if the user has seen it return true
                {
                    seen = true;
                }
            }
            return seen;
        }
    }
}
