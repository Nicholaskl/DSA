import java.util.*;

public class CircularTestHarness
{
    public static void main( String[] args)
    {
        try
        {
            CircularQueue myCQ = new CircularQueue(10);

            System.out.println("................Circular Queue Tests................");
            System.out.println("Testing Enqueue and peek. 200 expected");
            myCQ.enqueue(200);
            myCQ.enqueue(210);
            System.out.println(myCQ.peek());

            myCQ.enqueue(220);
            myCQ.enqueue(240);
            myCQ.enqueue(260);
            myCQ.enqueue(280);
            myCQ.enqueue(300);
            myCQ.enqueue(320);
            myCQ.enqueue(340);
            myCQ.enqueue(360);

            System.out.println("Testing Dequeue and peek. 210 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 220 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 240 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 260 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 280 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 300 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 320 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 340 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());

            System.out.println("Testing Dequeue and peek. 360 expected.");
            myCQ.dequeue();
            System.out.println(myCQ.peek());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
