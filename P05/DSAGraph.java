public class DSAGraph
{
    private DSALinkedList vertices;

    public DSAGraph()
    {
        vertices = new DSALinkedList();
    }

    public void addVertex(String label, Object value)
    {
        vertices.addLast(label, value);
    }

    public void addEdge(String label1, String label2)
    {
        Iterator iter = vertices.iterator();
        DSAGraphVertex temp1, temp2;

        while (iter.hasNext())
        {
            if(((iter.next()).getLabel).equals(label1))
            {
                temp1 = iter.next();
            }
            else if (((iter.next()).getLabel).equals(label2))
            {
                temp2 = iter.next();
            }

            if((temp1 != null) && (temp2 != null))
            {

            }
            else
            {
                System.out.println("Vertices not found");
            }
        }
    }

    public boolean hasVertex(String label)
    {
        boolean vertexB = false;
        Iterator iter = vertices.iterator();

        if(vertices.isEmpty())
        {
            throw new IllegalArgumentException("No vertices found")
        }
        else
        {
            while (iter.hasNext())
            {
                if(iter.next().equals(label))
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

    public DSAGraphVertex getVertex(label)
    {
        DSAGraphVertex vertex = null;
        Iterator iter = vertices.iterator();

        if(hasVertex(label))
        {
            while (iter.hasNext())
            {
                if(((iter.next()).getLabel).equals(label1))
                {
                    vertex = iter.next();
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
        DSAGraphVertex vertex;

        vertex = getVertex(label);

        return vertex.getAdjacent();
    }

    public boolean isAdjacent(String label1, String label2)
    {
        Iterator iter = vertices.iterator();
        boolean boolAdj = false;
        DSALinkedList vertex1 = null;
        DSALinkedList vertex2 = null

        vertex1 = getVertex(label1);
        vertex2 = getVertex(label2);

        if(hasVertex(label))
        {
            while (iter.hasNext())
            {
                if(((iter.next()).getLabel).equals(label1))
                {
                    vertex = iter.next();
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Label not Found");
        }

        return boolAdj;
    }

    private class DSAGraphVertex
    {
        private String label;
        private Object value;
        private DSALinkedList links;
        private boolean visited;

        public void DSAGraphVertex(String inLabel, Object inValue)
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
                throw new IllegalArgumentException("No Adjacent vertices")
            }
            else
            {
                linksTemp = links;
            }
            return linksTemp;
        }

        public void addEdge(DSAGraphVertex vertex)
        {
            links.addLast(vertex);
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
