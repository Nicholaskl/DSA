/*
 * File: DSAGraph.java
 * File Created: Monday, 30th September 2019
 * Author: Nicholas Klvana-Hooper
 * -----
 * Last Modified: Tuesday, 29th October 2019
 * Modified By: Nicholas Klvana-Hooper
 * -----
 * Purpose: A graph class for creating a Graph Data structure
 * Reference: A modified version of the graph used from Prac_05 made by Nicholas
 *            Klvana-Hooper(me) from 18/09/19. (Accessed on the 30th of September, 2019)
 *            based on Lecture 05 - graphs lecture slides (Accessed on the 30th of September, 2019)
 */

import java.util.*;

public class DSAGraph
{
    private DSALinkedList vertices;

        /*
        * SUBMODULE: DSAGraph
        * ASSERTION: The default constructor for the DSAGraph class
        */
    public DSAGraph()
    {
        vertices = new DSALinkedList(); //Creates a new linked list for vertices
    }

        /*
        * SUBMODULE: addVertex
        * IMPORT: label(String), value(Object)
        * EXPORT: void
        * ASSERTION: Adds a new vertex to the graph
        */
    public void addVertex(String label, Object value)
    {
        DSAGraphVertex newVertex;
        if(!hasVertex(label)) //if vertex already exists, don't add
        {
            newVertex = new DSAGraphVertex(label, value);
            vertices.insertLast(newVertex); //Add vertex to the list
        }
    }

    /*
    * SUBMODULE: addEdge
    * IMPORT: label1(String), label2(String)
    * EXPORT: void
    * ASSERTION: Adds an edge between two vertexes in the graph
    */
    public void addEdge(String label1, String label2)
    {
        DSAGraphVertex curr = null;

        if(isAdjacent(label1, label2)) //Checks if there already is an edge
        {
            System.out.println(label2 + " is already following " + label1);
        }
        if(hasVertex(label1) && hasVertex(label2)) //otherwise add the label
        {
            Iterator iter = vertices.iterator();
            while (iter.hasNext()) //traverse the linked list
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label1)) //if it equals the first vertex
                {
                    curr.addEdge(label2); //ad the second vertex to its adjacency list
                }
            }
        }
        else //otherwise one of the vertexes must not exist
        {
            throw new IllegalArgumentException("Vertex(es) not found!");
        }

    }

    /*
    * SUBMODULE: setNodeValue
    * IMPORT: label(String), value(Object)
    * EXPORT: void
    * ASSERTION: Adds a value to a vertex specified
    */
    public void setNodeValue(String label, Object value)
    {
        DSAGraphVertex curr = null;

        Iterator iter = vertices.iterator();
        while (iter.hasNext()) //traverse the vertices list
        {
            curr = (DSAGraphVertex)iter.next();
            if((curr.getLabel()).equals(label)) //if it equals the vertex requested
            {
                curr.setValue(value); //Sets the value for that vertex
            }
        }
    }

    /*
    * SUBMODULE: getNodeValue
    * IMPORT: label(String)
    * EXPORT: value(Object)
    * ASSERTION: Outputs the value for a specified vertex
    */
    public Object getNodeValue(String label)
    {
        DSAGraphVertex curr = null;
        Object value = null;

        Iterator iter = vertices.iterator();
        while (iter.hasNext()) //traverse the verticies list
        {
            curr = (DSAGraphVertex)iter.next();
            if((curr.getLabel()).equals(label)) //if the vertex is the one we want
            {
                value = curr.value; //store the value of the vertex
            }
        }
        if (value == null) //if value isn't set, node wasn't found
        {
            System.out.println("Node not found");
        }

        return value; //return the value of vertex requested
    }

    /*
    * SUBMODULE: deleteNode
    * IMPORT: label(String)
    * EXPORT: void
    * ASSERTION: Removes a vertex from the graph
    */
    public void deleteNode(String label)
    {
        int ii;
        DSALinkedList ll;
        DSAGraphVertex curr, currll;
        String currLabel;

        vertices.remove(find(label)); //first, remove the entire vertex from list

        Iterator iter = vertices.iterator();
        while(iter.hasNext()) //traverse the verticies iterator
        {
            curr = (DSAGraphVertex)iter.next();
            ll = getAdjacent(curr.getLabel()); //get the adjacent vertices to the current vertex
            Iterator iterll = ll.iterator();
            ii = -1; //sets to -1 so index 0 is the first element
            while (iterll.hasNext()) //now traverse the adjacent vertices
            {
                ii++; //increase the index number
                currLabel = (String)iterll.next();
                if((currLabel).equals(label)) //if current adjacent vertex is matched
                {
                    ll.remove(ii); //remove it from that adjacent list
                }
            }
            if(find(label) >= 0) //if index matches
            {
                ll.remove(find(label)); //remove it from list
            }
        }
    }

    /*
    * SUBMODULE: removeEdge
    * IMPORT: follower(String), following(String)
    * EXPORT: void
    * ASSERTION: Removes the edge between two vertexes
    */
    public void removeEdge(String follower, String following)
    {
        int ii;
        DSALinkedList ll;
        DSAGraphVertex curr, currll;
        String currLabel;

        Iterator iter = vertices.iterator();
        while(iter.hasNext()) //traverse the vertices list
        {
            curr = (DSAGraphVertex)iter.next();
            if(curr.label.equals(follower)) //if the follower vertex is found
            {
                ll = getAdjacent(curr.getLabel()); //get the adjacent vertices of follower
                Iterator iterll = ll.iterator();
                ii = -1; //sets to -1 so index 0 is the first element
                while (iterll.hasNext()) //traverse the adjacent vertices of the follower
                {
                    ii++; //sets to -1 so index 0 is the first element
                    currLabel = (String)iterll.next();
                    if((currLabel).equals(following)) //if adjacent vertex is one we remove
                    {
                        ll.remove(ii); //remove it from adjacent vertexes
                    }
                }
            }
        }
    }

    /*
    * SUBMODULE: find
    * IMPORT: label(String)
    * EXPORT: idxCount(int)
    * ASSERTION: Finds the index of the vertex in the vertices linked list
    */
    public int find(String label)
    {
        int idxCount = -1; //sets to -1 so index 0 is the first element
        boolean idxFound = false;
        DSAGraphVertex curr;

        if(hasVertex(label)) //Checks that the vertex exists
        {
            Iterator iter = vertices.iterator();
            while(iter.hasNext() && idxFound != true) //Traverses the vertex list making sure
            {                                         //if its been found, we stop searching
                idxCount++;
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label)) //if found the vertex
                {
                    idxFound = true; //label is found
                }
            }
        }
        return idxCount;
    }

    /*
    * SUBMODULE: hasVertex
    * IMPORT: label(String)
    * EXPORT: hasVertex(boolean)
    * ASSERTION: Returns whether the graph contains a specified vertex
    */
    public boolean hasVertex(String label)
    {
        boolean hasVertex = false;
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr;

        if(vertices.isEmpty()) //doesn't have vertex if no vertices exist
        {
            hasVertex = false;
        }
        else
        {
            while (iter.hasNext()) //traverse the vertex list
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label)) //if we find the vertex
                {
                    hasVertex = true; //set to found
                }
            }
        }
        return hasVertex;
    }

    /*
    * SUBMODULE: getVertexCount
    * IMPORT:
    * EXPORT: vCount(int)
    * ASSERTION: Returns the number of vertices in the graph
    */
    public int getVertexCount()
    {
        int vCount = 0;
        Iterator iter = vertices.iterator();

        if(vertices.isEmpty()) //If list is empty
        {
            throw new IllegalArgumentException("No vertices found");
        }
        else
        {
            while (iter.hasNext()) //iterate over the vertex list, increasing count
            {
                vCount += 1;
            }
        }

        return vCount;
    }

    /*
    * SUBMODULE: getVertex
    * IMPORT: label(String)
    * EXPORT: vertex(DSAGraphVertex)
    * ASSERTION: Returns the specified vertex from the graph
    */
    public DSAGraphVertex getVertex(String label)
    {
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr;
        DSAGraphVertex vertex = null;

        if(hasVertex(label)) //if the vertex exists
        {
            while (iter.hasNext()) //traverse the vertex list
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label)) //if found
                {
                    vertex = curr; //store vertex for output
                }
            }
        }
        else //otherwise can't get it
        {
            throw new IllegalArgumentException("Label not Found");
        }

        return vertex;
    }

    /*
    * SUBMODULE: getAdjacent
    * IMPORT: label(String)
    * EXPORT: vertices(DSALinkedList)
    * ASSERTION: Returns the list of vertices adjacent to the one specified
    */
    public DSALinkedList getAdjacent(String label)
    {
        DSAGraphVertex vertex = null;
        vertex = getVertex(label); //finds the vertex

        return vertex.getAdjacent(); //returns the adjacent vertices in list
    }

    /*
    * SUBMODULE: isAdjacent
    * IMPORT: label1(String), label2(String)
    * EXPORT: boolAdj(boolean)
    * ASSERTION: Returns whether two vertexes are adjacent
    */
    public boolean isAdjacent(String label1, String label2)
    {
        boolean boolAdj = false;
        DSAGraphVertex vertex;
        String currAdj;
        DSALinkedList adjL;

        if((hasVertex(label1)) && (hasVertex(label2))) //both vertices exist for an edge to
        {
            vertex = getVertex(label1);
            adjL = vertex.getAdjacent(); //Adjacency list of the vertex of the vertex

            Iterator iter = adjL.iterator();
            while (iter.hasNext()) //traverse adjacency list
            {
                currAdj = (String)iter.next();
                if(currAdj.equals(label2)) //if other node is found
                {
                    boolAdj = true; //There must be an edge
                }
            }
        }
        else //Both vertices not found, so no edge
        {
            throw new IllegalArgumentException("Label not Found");
        }

        return boolAdj;
    }

    /*
    * SUBMODULE: sortGraph
    * IMPORT:
    * EXPORT:
    * ASSERTION: Sorts the graph by numerical value
    */
    public void sortGraph()
    {
        DSAGraphVertex curr, currSorted, sortedFirst, sortedLast;
        Iterator iter = vertices.iterator();
        DSALinkedList sortedList = new DSALinkedList();
        DSALinkedList tempList = new DSALinkedList();

        while (iter.hasNext()) //iterate through vertex list
        {
            curr = (DSAGraphVertex)iter.next();

            if(sortedList.isEmpty()) //if the sortedList is empty, then next item will be sorted
            {
                sortedList.insertFirst(curr); //so add it
            }
            else
            {
                sortedFirst = (DSAGraphVertex)sortedList.peekFirst();
                sortedLast = (DSAGraphVertex)sortedList.peekLast();

                if ((curr.getLabel()).compareTo(sortedLast.getLabel()) > 0)
                { //if new vertex is bigger than all the sorted Elements
                    sortedList.insertLast(curr); //insert after all of them
                }
                else if (((curr.getLabel()).compareTo(sortedFirst.getLabel()) < 0))
                { //if new vertex is smaller than all the sorted Elements
                    sortedList.insertFirst(curr); //insert it at beginning of sorted list
                }
                else
                {
                    while ((curr.getLabel()).compareTo(sortedLast.getLabel()) < 0)
                    { //Takes of largest sorted vertexes until the new one is the bigger than the sorted list values
                        tempList.insertFirst(sortedList.removeLast());//take off end of sorted list
                        sortedLast = (DSAGraphVertex)sortedList.peekLast();
                    }
                    sortedList.insertLast(curr); //now insert as its the largest value in the list sorted
                    while(!tempList.isEmpty()) //add back all of the other sorted values after
                    {
                        sortedList.insertLast(tempList.removeFirst());
                    }
                }
            }
        }
        vertices = sortedList;
    }

    /*
    * SUBMODULE: getVertices
    * IMPORT:
    * EXPORT: vertexList(DSALinkedList)
    * ASSERTION: Returns the verticies list
    */
    public DSALinkedList getVertices()
    {
        DSALinkedList vertexList = new DSALinkedList();
        DSAGraphVertex curr;

        if(vertices.isEmpty()) //can't get no vertices
        {
            System.out.println("Empty list, can't get vertices");
        }
        else
        {
            Iterator iter = vertices.iterator();
            while (iter.hasNext()) //traverse vertices
            {
                curr = (DSAGraphVertex)iter.next();
                vertexList.insertLast(curr.getValue()); //add them to a linkedlist
            }
        }

        return vertexList; //return vertex list
    }

    /*
    * SUBMODULE: displayAsList
    * IMPORT:
    * EXPORT:
    * ASSERTION: Prints the graph as an adjacency list
    */
    public void displayAsList()
    {
        sortGraph();
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr, currll;
        DSALinkedList ll = null;

        while (iter.hasNext()) //traverse vertexes
        {
            curr = (DSAGraphVertex)iter.next();
            System.out.print(curr.getLabel() + "| "); //print parent vertex, then line

            ll = getAdjacent(curr.getLabel());
            if(!ll.isEmpty()) //print only if there are adjacent vertices
            {
                Iterator iterll = ll.iterator();
                while (iterll.hasNext()) //traverse adjacent vertices
                {
                    System.out.print(iterll.next() + " "); //print all adjacent vertices
                }
            }
            System.out.print("\n"); //end of vertices edges
        }
    }

    /*
    * SUBMODULE: displayAsMatrix
    * IMPORT:
    * EXPORT:
    * ASSERTION: Displays the graph as an adjacency matrix
    */
    public void displayAsMatrix()
    {
        sortGraph(); //Sort graph to make sure in order for proper display
        Iterator iter = vertices.iterator();
        Iterator iter2 = vertices.iterator();
        DSAGraphVertex curr, curr2;
        DSALinkedList ll;
        String currLabel;
        Boolean isMatrix;

        System.out.print(" ");
        while (iter.hasNext()) //traverse vertex list
        {
            curr = (DSAGraphVertex)iter.next();

            System.out.print(" " + curr.getLabel()); //print all vertices for top row
        }
        System.out.print("\n"); //end of top row

        iter = vertices.iterator();
        while (iter.hasNext()) //restart traversal of vertex list
        {
            curr = (DSAGraphVertex)iter.next();
            System.out.print(curr.getLabel() + " "); //print vertex

            iter2 = vertices.iterator();
            while (iter2.hasNext()) //traverses the vertices again
            {
                isMatrix = false;
                curr2 = (DSAGraphVertex)iter2.next();

                ll = getAdjacent(curr.getLabel());
                Iterator iterll = ll.iterator(); //traverses the adjacent vertex
                while (iterll.hasNext())
                {
                    currLabel = (String)iterll.next();
                    if((curr2.getLabel()).equals(currLabel)) //if vertex is found
                    {
                        isMatrix = true;
                    }
                }
                if (isMatrix == true) //if edge exists print 1
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 "); //otherwise print 0
                }
            }
            System.out.print("\n"); //end of line
        }
    }

    /*
    * SUBMODULE: depthFirstSearch
    * IMPORT:
    * EXPORT:
    * ASSERTION: Performs a depth first search traversal on the graph
    */
    public void depthFirstSearch()
    {
        sortGraph(); //make sure data is sorted
        DSAGraphVertex curr;
        DSAStack stack = new DSAStack();

        Iterator iter = vertices.iterator();
        while (iter.hasNext()) //traverses vertex list
        {
            curr = (DSAGraphVertex)iter.next();
            curr.clearVisited(); //clear the visited for all of the vertices before start
        }

        curr = (DSAGraphVertex)vertices.peekFirst();
        curr.setVisited(); //set first vertex to visited
        stack.push(curr); //place on stack
        depthFirstSearchRec(stack); //Recursively depth first search from the first vertex
        System.out.print("\n");
    }

    /*
    * SUBMODULE: depthFirstSearchRec
    * IMPORT: stack(DSAStack)
    * EXPORT:
    * ASSERTION: Recursive algorithm of the depth first search
    */
    public void depthFirstSearchRec(DSAStack stack)
    {
        DSALinkedList adjacent;
        DSAGraphVertex currAdj;
        DSAGraphVertex v = (DSAGraphVertex)stack.top();
        v.setVisited(); //visiting next vertex

        adjacent = v.getAdjacent(); //find adjacent vertices
        Iterator iterAdj = adjacent.iterator();
        while (iterAdj.hasNext()) //traverse adjacent vertices
        {
            currAdj = getVertex((String)iterAdj.next());
            if(!currAdj.getVisited()) //if it hasn't been visited
            {
                System.out.print("(" + v.getLabel() + "," + currAdj.getLabel() + ") "); //print the edge
                stack.push(currAdj); //then add the vertex to the to check stack
                depthFirstSearchRec(stack); //then go back and check the next vertex in stack
            }
        }
        stack.pop(); //no longer need to check the vertex
    }

    /*
    * SUBMODULE: breadthFirstSearch
    * IMPORT:
    * EXPORT:
    * ASSERTION: Peforms a breath first search traversal on the graph
    */
    public void breadthFirstSearch()
    {
        sortGraph(); //make sure graph is sorted
        DSAGraphVertex curr;
        DSAQueue queue = new DSAQueue();

        Iterator iter = vertices.iterator();
        while (iter.hasNext()) //traverse the vertex list
        {
            curr = (DSAGraphVertex)iter.next();
            curr.clearVisited(); //clear visited on all the nodes
        }

        curr = (DSAGraphVertex)vertices.peekFirst();
        curr.setVisited(); //set first vertex to found as we are checking
        queue.enqueue(curr);
        breadthFirstSearchRec(queue); //call Recursive depth algorithm on first vertex
        System.out.print("\n"); //end of traversal
    }

    /*
    * SUBMODULE: breadthFirstSearchRec
    * IMPORT: queue(DSAQueue)
    * EXPORT:
    * ASSERTION: Peforms a breadth first search traversal on the graph
    */
    public void breadthFirstSearchRec(DSAQueue queue)
    {
        DSALinkedList adjacent;
        DSAGraphVertex currAdj;
        DSAGraphVertex v;

        if(!queue.isEmpty()) //continue if still more vertexes to check
        {
            v = (DSAGraphVertex)queue.peek();
            v.setVisited(); //visited the next node

            adjacent = v.getAdjacent();
            Iterator iterAdj = adjacent.iterator(); //get the adjacent vertices
            while (iterAdj.hasNext()) //traverse the adjacent vertices
            {
                currAdj = getVertex((String)iterAdj.next()); //get the vertex needed

                if(!currAdj.getVisited()) //if not visited
                {
                    queue.enqueue(currAdj); //add it to be checked
                    System.out.print("(" + v.getLabel() + "," + currAdj.getLabel() + ") "); //print the edge
                    currAdj.setVisited(); //vertex has been visited
                }
            }
            queue.dequeue(); //no longer need to check vertex so remove
            breadthFirstSearchRec(queue); //check the remaining values in the queue
        }
    }

    private class DSAGraphVertex
    {
        private String label;
        private DSALinkedList links;
        private boolean visited;
        private Object value;

        /*
        * SUBMODULE: DSAGraphVertex
        * IMPORT: inLabel(String), inValue(Object)
        * ASSERTION: Alternate constructor for the DSAGraphVertex class
        */
        public DSAGraphVertex(String inLabel, Object inValue)
        {
            label = inLabel;
            links = new DSALinkedList();
            visited = false;
            value = inValue;
        }

        public String getLabel() // returns the Label of the vertex
        {
            return label;
        }

        public Object getValue() // returns the value of the vertex
        {
            return value;
        }

        /*
        * SUBMODULE: getAdjacent
        * IMPORT:
        * EXPORT: linksTemp(DSALinkedList)
        * ASSERTION: Returns the list of vertices adjacent to the current vertex
        */
        public DSALinkedList getAdjacent()
        {
            sortAdjacency();
            DSALinkedList linksTemp = new DSALinkedList();
            if(!links.isEmpty()) //if there are adjacent vertices
            {
                linksTemp = links; //get the list
            }
            return linksTemp;
        }

        /*
        * SUBMODULE: addEdge
        * IMPORT: vertexLabel(String)
        * EXPORT:
        * ASSERTION: Adds the adjacent vertex to the current vertex list
        */
        public void addEdge(String vertexLabel)
        {
            links.insertLast(vertexLabel);
        }

        public void setVisited() //Sets visited for the vertex
        {
            visited = true;
        }

        public void setValue(Object inValue) //Sets value for the vertex
        {
            value = inValue;
        }

        public void clearVisited() // Clears visited from the vertex
        {
            visited = false;
        }

        public boolean getVisited() //Returns whether the vertex has been visited
        {
            return visited;
        }

        /*
        * SUBMODULE: toString
        * IMPORT:
        * EXPORT: str(String)
        * ASSERTION: Outputs the adjacent vertexes as a String
        */
        public String toString() //Prints out the vertex data
        {
            String str = "";

            Iterator iter = links.iterator();
            while (iter.hasNext()) //traverse adjacency list
            {
                str += iter.next(); //print the adjacent vertexes
            }

            return str;
        }

        /*
        * SUBMODULE: sortAdjacency
        * IMPORT:
        * EXPORT:
        * ASSERTION: Sorts the adjacency list by numerical value
        */
        public void sortAdjacency()
        {
            Iterator iter = links.iterator();
            String curr, currSorted, sortedFirst, sortedLast;
            DSALinkedList sortedList = new DSALinkedList();
            DSALinkedList tempList = new DSALinkedList();

            while (iter.hasNext()) //traverse the adjacent vertices
            {
                curr = (String)iter.next();

                if(sortedList.isEmpty()) //if there is no sorted element, list will be sorted when added
                {
                    sortedList.insertFirst(curr);
                }
                else
                {
                    sortedFirst = (String)sortedList.peekFirst();
                    sortedLast = (String)sortedList.peekLast();

                    if ((curr).compareTo(sortedLast) > 0)
                    { //if current vertex is bigger than the last element in sorted list
                        sortedList.insertLast(curr); //add it to the end
                    }
                    else if (((curr).compareTo(sortedFirst) < 0))
                    {//if current vertex is smaller than the first element in sorted list
                        sortedList.insertFirst(curr); //add it to the beginning
                    }
                    else
                    {
                        while ((curr).compareTo(sortedLast) < 0)
                        { //while the current vertex is smaller than the last element in the sorted list
                            tempList.insertFirst(sortedList.removeLast()); //take element of sorted and add it to temp
                            sortedLast = (String)sortedList.peekLast(); //now use the new last vertex
                        }
                        sortedList.insertLast(curr); //now its biggest, so add
                        while(!tempList.isEmpty()) //and add back all sorted vertices that were removed
                        {
                            sortedList.insertLast(tempList.removeFirst());
                        }
                    }
                }
            }
            links = sortedList;
        }
    }
}
