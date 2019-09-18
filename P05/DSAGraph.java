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

        if((hasVertex(label1) == false) || (hasVertex(label2) == false))
        {
            if ((hasVertex(label1) == false) && (hasVertex(label2) == true))
            {
                addVertex(label1);
            }
            else if ((hasVertex(label1) == true) && (hasVertex(label2) == false))
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
                    curr.addEdge(label2);
                }
                else if ((curr.getLabel()).equals(label2))
                {
                    curr.addEdge(label1);
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
        Iterator iter = vertices.iterator();
        boolean boolAdj = false;
        DSAGraphVertex vertex1, vertex2, vertex;
        Object curr;

        vertex1 = getVertex(label1);
        vertex2 = getVertex(label2);

        if((hasVertex(label1)) && (hasVertex(label2)))
        {
            while (iter.hasNext())
            {
                curr = iter.next();
                if(curr.equals(label1))
                {
                    vertex = (DSAGraphVertex)iter.next();
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Label not Found");
        }

        return boolAdj;
    }

    public void displayAsList()
    {
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
    }
}
