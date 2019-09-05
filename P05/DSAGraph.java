public class DSAGraph
{
    private DSALinkedList vertices;

    public DSAGraph()
    {
        vertices = new DSALinkedList();
    }

    public void addVertex()
    {
    }

    public void addEdge()
    {
    }

    public boolean hasVertex()
    {
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

        public getAdjacent()
        {
        }
    }
}
