import java.util.*;

public class DSAGraphnew
{
    private DSALinkedList vertices;

    //Default constructor
    public DSAGraphnew()
    {
        vertices = new DSALinkedList();
    }

    public void addVertex(String label, Object value)
    {
        DSAGraphVertex newVertex;
        newVertex = new DSAGraphVertex(label, value);
        vertices.insertLast(newVertex);
    }

    public void addEdge(String label1, String label2)
    {
        Iterator iter = vertices.iterator();

        if(hasVertex())

        while (iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }

    private class DSAGraphVertex
    {
        private String label;
        private Object value;
        private DSALinkedList links;
        private boolean visited;

        public DSAGraphVertex(String inLabel, Object inValue)
        {
            label = inLabel;
            value = inValue;
            links = new DSALinkedList();
            visited = false;
        }

        public String getLabel()
        {
            return label;
        }

        public Object getValue()
        {
            return value;
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

        public void addEdge(DSAGraphVertex vertex)
        {
            links.insertLast(vertex);
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
