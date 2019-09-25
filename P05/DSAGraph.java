import java.util.*;

public class DSAGraph
{
    private DSALinkedList vertices;

    public DSAGraph()
    {
        vertices = new DSALinkedList();
    }

    public void addVertex(String label)
    {
        DSAGraphVertex newVertex;
        newVertex = new DSAGraphVertex(label);
        vertices.insertLast(newVertex);
    }

    public void addEdge(String label1, String label2)
    {
        DSAGraphVertex curr = null;

        if((!hasVertex(label1)) || (!hasVertex(label2)))
        {
            if ((!hasVertex(label1)) && (hasVertex(label2)))
            {
                addVertex(label1);
            }
            else if ((hasVertex(label1)) && (!hasVertex(label2)))
            {
                addVertex(label2);
            }
            else
            {
                addVertex(label1);
                addVertex(label2);
            }

            Iterator iter = vertices.iterator();
            while (iter.hasNext())
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label1))
                {
                    curr.addEdge(label2);
                }
                else if ((curr.getLabel()).equals(label2))
                {
                    curr.addEdge(label1);
                }
            }
        }
        else
        {
            Iterator iter = vertices.iterator();
            while (iter.hasNext())
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label1))
                {
                    if(!isAdjacent(label1, label2))
                    {
                            curr.addEdge(label2);
                    }
                }
                else if ((curr.getLabel()).equals(label2))
                {
                    if(!isAdjacent(label2, label1))
                    {
                            curr.addEdge(label1);
                    }
                }
            }
        }
    }

    public boolean hasVertex(String label)
    {
        boolean vertexB = false;
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr;

        if(vertices.isEmpty())
        {
            vertexB = false;
        }
        else
        {
            while (iter.hasNext())
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label))
                {
                    vertexB = true;
                }
            }
        }

        return vertexB;
    }

    public int getVertexCount()
    {
        int vCount = 0;
        Iterator iter = vertices.iterator();

        if(vertices.isEmpty())
        {
            throw new IllegalArgumentException("No vertices found");
        }
        else
        {
            while (iter.hasNext())
            {
                vCount += 1;
            }
        }

        return vCount;
    }

    public DSAGraphVertex getVertex(String label)
    {
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr;
        DSAGraphVertex vertex = null;

        if(hasVertex(label))
        {
            while (iter.hasNext())
            {
                curr = (DSAGraphVertex)iter.next();
                if((curr.getLabel()).equals(label))
                {
                    vertex = curr;
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Label not Found");
        }

        return vertex;
    }

    public DSALinkedList getAdjacent(String label)
    {
        DSAGraphVertex vertex = null;
        vertex = getVertex(label);

        return vertex.getAdjacent();
    }

    public boolean isAdjacent(String label1, String label2)
    {
        boolean boolAdj = false;
        DSAGraphVertex vertex;
        String currAdj;
        DSALinkedList adjL;

        if((hasVertex(label1)) && (hasVertex(label2)))
        {
            vertex = getVertex(label1);
            adjL = vertex.getAdjacent();

            Iterator iter = adjL.iterator();
            while (iter.hasNext())
            {
                currAdj = (String)iter.next();
                if(currAdj.equals(label2))
                {
                    boolAdj = true;
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Label not Found");
        }

        return boolAdj;
    }

    public void sortGraph()
    {
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr, currSorted, sortedFirst, sortedLast;
        DSALinkedList sortedList = new DSALinkedList();
        DSALinkedList tempList = new DSALinkedList();

        while (iter.hasNext())
        {
            curr = (DSAGraphVertex)iter.next();

            if(sortedList.isEmpty())
            {
                sortedList.insertFirst(curr);
            }
            else
            {
                sortedFirst = (DSAGraphVertex)sortedList.peekFirst();
                sortedLast = (DSAGraphVertex)sortedList.peekLast();

                if ((curr.getLabel()).compareTo(sortedLast.getLabel()) > 0)
                {
                    sortedList.insertLast(curr);
                }
                else if (((curr.getLabel()).compareTo(sortedFirst.getLabel()) < 0))
                {
                    sortedList.insertFirst(curr);
                }
                else
                {
                    while ((curr.getLabel()).compareTo(sortedLast.getLabel()) < 0)
                    {
                        tempList.insertFirst(sortedList.removeLast());
                        sortedLast = (DSAGraphVertex)sortedList.peekLast();
                    }
                    sortedList.insertLast(curr);
                    while(!tempList.isEmpty())
                    {
                        sortedList.insertLast(tempList.removeFirst());
                    }
                }
            }
        }
        vertices = sortedList;
    }

    public void displayAsList()
    {
        sortGraph();
        Iterator iter = vertices.iterator();
        DSAGraphVertex curr, currll;
        DSALinkedList ll = null;

        while (iter.hasNext())
        {
            curr = (DSAGraphVertex)iter.next();

            System.out.print(curr.getLabel() + " | ");

            ll = getAdjacent(curr.getLabel());
            Iterator iterll = ll.iterator();
            while (iterll.hasNext())
            {
                System.out.print(iterll.next());
            }
            System.out.print("\n");
        }
    }

    public void displayAsMatrix()
    {
        sortGraph();
        Iterator iter = vertices.iterator();
        Iterator iter2 = vertices.iterator();
        DSAGraphVertex curr, curr2;
        DSALinkedList ll;
        String currLabel;
        Boolean isMatrix;

        System.out.print(" ");
        while (iter.hasNext())
        {
            curr = (DSAGraphVertex)iter.next();

            System.out.print(" " + curr.getLabel());
        }
        System.out.print("\n");

        iter = vertices.iterator();
        while (iter.hasNext())
        {
            curr = (DSAGraphVertex)iter.next();
            System.out.print(curr.getLabel() + " ");

            iter2 = vertices.iterator();
            while (iter2.hasNext())
            {
                isMatrix = false;
                curr2 = (DSAGraphVertex)iter2.next();

                ll = getAdjacent(curr.getLabel());
                Iterator iterll = ll.iterator();
                while (iterll.hasNext())
                {
                    currLabel = (String)iterll.next();
                    if((curr2.getLabel()).equals(currLabel))
                    {
                        isMatrix = true;
                    }
                }
                if (isMatrix == true)
                {
                    System.out.print("1 ");
                }
                else
                {
                    System.out.print("0 ");
                }
            }
            System.out.print("\n");
        }
    }

    public void depthFirstSearch()
    {
        sortGraph();
        DSAGraphVertex curr;
        DSAStack stack = new DSAStack();

        Iterator iter = vertices.iterator();
        while (iter.hasNext())
        {
            curr = (DSAGraphVertex)iter.next();
            curr.clearVisited();
        }

        curr = (DSAGraphVertex)vertices.peekFirst();
        curr.setVisited();
        stack.push(curr);
        depthFirstSearchRec(stack);
        System.out.print("\n");
    }

    public void depthFirstSearchRec(DSAStack stack)
    {
        DSALinkedList adjacent;
        DSAGraphVertex currAdj;
        DSAGraphVertex v = (DSAGraphVertex)stack.top();
        v.setVisited();

        adjacent = v.getAdjacent();
        Iterator iterAdj = adjacent.iterator();
        while (iterAdj.hasNext())
        {
            currAdj = getVertex((String)iterAdj.next());
            if(!currAdj.getVisited())
            {
                System.out.print("(" + v.getLabel() + "," + currAdj.getLabel() + ") ");
                stack.push(currAdj);
                depthFirstSearchRec(stack);
            }
        }
        stack.pop();
    }

    public void breadthFirstSearch()
    {
        sortGraph();
        DSAGraphVertex curr;
        DSAQueue queue = new DSAQueue();

        Iterator iter = vertices.iterator();
        while (iter.hasNext())
        {
            curr = (DSAGraphVertex)iter.next();
            curr.clearVisited();
        }

        curr = (DSAGraphVertex)vertices.peekFirst();
        curr.setVisited();
        queue.enqueue(curr);
        breadthFirstSearchRec(queue);
        System.out.print("\n");
    }

    public void breadthFirstSearchRec(DSAQueue queue)
    {
        DSALinkedList adjacent;
        DSAGraphVertex currAdj;
        DSAGraphVertex v;

        if(!queue.isEmpty())
        {
            v = (DSAGraphVertex)queue.peek();
            v.setVisited();

            adjacent = v.getAdjacent();
            Iterator iterAdj = adjacent.iterator();
            while (iterAdj.hasNext())
            {
                currAdj = getVertex((String)iterAdj.next());

                if(!currAdj.getVisited())
                {
                    queue.enqueue(currAdj);
                    System.out.print("(" + v.getLabel() + "," + currAdj.getLabel() + ") ");
                    currAdj.setVisited();
                }
            }
            queue.dequeue();
            breadthFirstSearchRec(queue);
        }
    }

    private class DSAGraphVertex
    {
        private String label;
        private DSALinkedList links;
        private boolean visited;

        public DSAGraphVertex(String inLabel)
        {
            label = inLabel;
            links = new DSALinkedList();
            visited = false;
        }

        public String getLabel()
        {
            return label;
        }

        public DSALinkedList getAdjacent()
        {
            sortAdjacency();
            DSALinkedList linksTemp = null;
            if(links.isEmpty())
            {
                throw new IllegalArgumentException("No Adjacent vertices");
            }
            else
            {
                linksTemp = links;
            }
            return linksTemp;
        }

        public void addEdge(String vertexLabel)
        {
            links.insertLast(vertexLabel);
        }

        public void setVisited()
        {
            visited = true;
        }

        public void clearVisited()
        {
            visited = false;
        }

        public boolean getVisited()
        {
            return visited;
        }

        public String toString()
        {
            String str = "";

            Iterator iter = links.iterator();
            while (iter.hasNext())
            {
                str += iter.next();
            }

            return str;
        }

        public void sortAdjacency()
        {
            Iterator iter = links.iterator();
            String curr, currSorted, sortedFirst, sortedLast;
            DSALinkedList sortedList = new DSALinkedList();
            DSALinkedList tempList = new DSALinkedList();

            while (iter.hasNext())
            {
                curr = (String)iter.next();

                if(sortedList.isEmpty())
                {
                    sortedList.insertFirst(curr);
                }
                else
                {
                    sortedFirst = (String)sortedList.peekFirst();
                    sortedLast = (String)sortedList.peekLast();

                    if ((curr).compareTo(sortedLast) > 0)
                    {
                        sortedList.insertLast(curr);
                    }
                    else if (((curr).compareTo(sortedFirst) < 0))
                    {
                        sortedList.insertFirst(curr);
                    }
                    else
                    {
                        while ((curr).compareTo(sortedLast) < 0)
                        {
                            tempList.insertFirst(sortedList.removeLast());
                            sortedLast = (String)sortedList.peekLast();
                        }
                        sortedList.insertLast(curr);
                        while(!tempList.isEmpty())
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
