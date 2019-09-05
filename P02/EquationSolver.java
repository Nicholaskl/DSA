import java.util.*;

public class EquationSolver
{
    public static void main( String[] args)
    {
        solve("5 + 5");
    }

    public static double solve(String equation)
    {
        double ans=0.0;
        DSAQueue postfix = new CircularQueue();

        postfix = parseInfixtoPostfix(equation);
        System.out.println(postfix.peek());
        //ans = evaluatePostfix();

        return ans;
    }

    private static char parseNextTerm(String parseTerm)
    {
        return parseTerm.charAt(0);
    }

    private static DSAQueue parseInfixtoPostfix(String equation)
    {
        CircularQueue postfix = new CircularQueue();
        DSAStack opStack = new DSAStack();

        String[] bits = equation.split(" ");
        char term;

        for(int ii = 0; ii < bits.length; ii++)
        {
            opStack.push(bits[ii]);
        }

        while(! opStack.isEmpty())
        {
            if(opStack.top() instanceof Character)
            {
                term = (char)opStack.pop();
            }
            else
            {
                term = parseNextTerm((String)opStack.pop());
            }
            System.out.println(term);
            switch(term)
            {
                case '(':
                    opStack.push('(');
                break;
                case ')':
                    while ((char)opStack.top() != '(')
                    {
                        postfix.enqueue(opStack.pop());
                    }
                    opStack.pop();
                break;
                case '+': case '-': case '*': case '/':
                    while ((! opStack.isEmpty()) && (term != '(') &&
                          ((precedenceOf((char)opStack.top())) >= precedenceOf(term)))
                    {
                        postfix.enqueue(opStack.pop());
                    }
                    opStack.push(term);
                break;
                default:
                    postfix.enqueue(Double.valueOf(term));
            }
        }
        while(! opStack.isEmpty())
        {
            postfix.enqueue(opStack.pop());
        }
        return postfix;
    }

    private double evaluatePostfix(DSAQueue postfixQueue)
    {
        DSAStack operandStack = new DSAStack();
        double op1, op2;
        char op;
        double cop = 5.0;

        while(! postfixQueue.isEmpty())
        {
            if(postfixQueue.peek() instanceof Double)
            {
                operandStack.push(postfixQueue.peek());
            }
            else if (postfixQueue.peek() instanceof Character)
            {
                op1 = (double)operandStack.pop();
                op2 = (double)operandStack.pop();
                op = (char)postfixQueue.dequeue();

                operandStack.push(executeOperation(op, op1, op2));
            }
        }
        return cop;
    }

    private static int precedenceOf(char theOp)
    {
        int precedence = 0;

        switch(theOp)
        {
            case '+': case '-':
                precedence = 1;
            break;
            case '*': case '/':
                precedence = 2;
            break;
            default:
                System.out.println("Error in precedence");
                precedence = 3;
        }
        return precedence;
    }

    private double executeOperation(char op, double op1, double op2)
    {
        double ans = 0;
        switch(op)
        {
            case '+':
                ans = op1 + op2;
            break;
            case '-':
                ans = op1 - op2;
            break;
            case '*':
                ans = op1 * op2;
            break;
            case '/':
                ans = op1 / op2;
            break;
            default:
                System.out.println("Error in operator");
        }
        return ans;
    }


}
