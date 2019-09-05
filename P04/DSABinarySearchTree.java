import java.io.*;
public class DSABinarySearchTree implements Serializable
{   
    private int nodeCount;

    private class DSATreeNode implements Serializable

    {
        private String key;
        private Object value;
        private DSATreeNode leftChild;
        private DSATreeNode rightChild;

        public DSATreeNode(String inKey, Object inValue)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("Key cannot be null");
            }
            key = inKey;
            value = inValue;
            rightChild = null;
            leftChild = null;
        }

        public String getKey()
        {
            return key;
        }
    
        public Object getValue()
        {
            return value;
        }

        public DSATreeNode getLeft()
        {
            return leftChild;
        }

        public void setLeft(DSATreeNode newLeft)
        {
            leftChild = newLeft;
        }

        public DSATreeNode getRight()
        {
            return rightChild;
        }
    
        public void setRight(DSATreeNode newRight)
        {   
            rightChild = newRight;
        }
    }

    private DSATreeNode root;

    public DSABinarySearchTree()
    {
        root = null;
        nodeCount = 0;
    }

    public Object find(String key)
    {
        return findRec(key, root);
    }

    private Object findRec(String key, DSATreeNode currNode)
    {
        Object value = null;

        if(currNode == null)
        {
            throw new IllegalArgumentException("Key" + key + "not found");
        }
        else if(key.equals(currNode.getKey()))
        {
            value = currNode.getValue();
        }
        else if(key.compareTo(currNode.getKey()) < 0)
        {
            value = findRec(key, currNode.getLeft());
        }
        else
        {
            value = findRec(key, currNode.getRight());
        }
        return value;
    }

    public void insert(String key, Object data)
    {
        root = insertRec(key, data, root);
        nodeCount += 1;
    }                                        

    public int getNodeCount()
    {
        return nodeCount;
    }    

    private DSATreeNode insertRec(String key, Object data, DSATreeNode currNode)

    {
        DSATreeNode updateNode;
        updateNode = currNode;

        if(currNode == null)
        {
            DSATreeNode newNode = new DSATreeNode(key, data);
            updateNode = newNode;
        }
        else if(key.equals(currNode.getKey()))
        {
            throw new IllegalArgumentException("Key" + key + "not found");
        }
        else if(key.compareTo(currNode.getKey()) < 0)
        {
            currNode.setLeft(insertRec(key, data, currNode.getLeft()));
        }
        else
        {
            currNode.setRight(insertRec(key, data, currNode.getRight()));
        }

        return updateNode;
    }

    public String printPostorder()
    {
        return printPostorderRec(root);
    }

    public String printPostorderRec(DSATreeNode currNode)
    {
        String output, left, right;
        if(currNode != null)
        {
            left = printPostorderRec(currNode.getLeft());
            right = printPostorderRec(currNode.getRight());
            output = left + right + currNode.getKey() + ", " + currNode.getValue() + "\n";
            
        }
        else
        {
            output = "";
        }
        return output;
    }

    public String printInorder()
    {
        return printInorderRec(root);
    }

    public String printInorderRec(DSATreeNode currNode)
    {
        String output, left, right;
        if(currNode != null)
        {
            left = printInorderRec(currNode.getLeft());
            right = printInorderRec(currNode.getRight());
            output = left + currNode.getKey() + ", " + currNode.getValue() + "\n" + right;

        }
        else
        {
            output = "";
        }
        return output;
    }

    public String printPreorder()
    {
        return printPreorderRec(root);
    }

    public String printPreorderRec(DSATreeNode currNode)
    {
        String output, left, right;
        if(currNode != null)
        {
            left = printPreorderRec(currNode.getLeft());
            right = printPreorderRec(currNode.getRight());
            output = currNode.getKey() + ", " + currNode.getValue() + "\n" + left + right;

        }
        else
        {
            output = "";
        }
        return output;
    }

    public String min()
    {
        return minRec(root);
    }

    public String minRec(DSATreeNode currNode)
    {
        String minKey;
        if (currNode.getLeft() != null)
        {
            minKey = minRec(currNode.getLeft());
        }
        else
        {
            minKey = currNode.getKey();
        }
        return minKey;
    }

    public String max()
    {
        return maxRec(root);
    }
    
    public String maxRec(DSATreeNode currNode)
    {
        String maxKey;
        if (currNode.getRight() != null)
        {
            maxKey = maxRec(currNode.getRight());
        }
        else
        {
            maxKey = currNode.getKey();
        }
        return maxKey;
    }
    
    public int height()
    {
        return heightRec(root);
    }

    public int heightRec(DSATreeNode currNode)
    {
        int htSoFar, iLeftHt, iRightHt;

        if(currNode == null)
        {
            htSoFar = -1;
        }
        else
        {
            iLeftHt = heightRec(currNode.getLeft());
            iRightHt = heightRec(currNode.getRight());

            if (iLeftHt > iRightHt)
            {            
                htSoFar = iLeftHt + 1;
            }
            else
            {
                htSoFar = iRightHt + 1;
            }
        }
        return htSoFar;           
    }

    public double balance()
    {
        double balanceNum;
        balanceNum = 0;

        System.out.println(balanceRec(root));
        balanceNum = ((double)balanceRec(root) / ((double)Math.pow(2, 1 + height())));

        return balanceNum * 100.0;
    }

    public int balanceRec(DSATreeNode currNode)
    {
        int noChild, leftChild, rightChild;

        if(currNode == null)
        {
            noChild = 1;
        }
        else
        {
            leftChild = balanceRec(currNode.getLeft());
            rightChild = balanceRec(currNode.getRight());

            noChild = leftChild + rightChild;
        }
        return noChild;       
    }
}
