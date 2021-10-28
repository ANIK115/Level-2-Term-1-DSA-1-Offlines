package com.company;

public class ArithmeticExpression {

    public int checkPrecedence(char op) //function for checking precedence of the operators
    {
        if(op=='*' || op=='/')
            return 2;
        if(op=='+' || op=='-')
            return 1;
        return 0;
    }
    public boolean isOperator(char c)
    {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    public boolean isNumber(char c)
    {
        return c >= '0' && c <= '9';
    }
    public boolean isFraction(char c) //is the number is going to be fractional or not
    {
        return c=='.';
    }
    public float binaryOperation(float a, float b, char op)
    {
        if(op=='+')
            return a+b;
        else if(op=='-')
            return a-b;
        else if(op=='*')
            return a*b;
        else
            return a/b; //if b is 0 then it will return infinity which is handled later
    }
    public boolean formattingOperation(Stack<Character> operators, Stack<Float> operands)
    {
        float a,b;
        if(operands.isEmpty())
        {
            System.out.println("Not valid.");
            return false;
        }else
        {
            b = operands.top();
            operands.pop();
        }
        if(operands.isEmpty())
        {
            System.out.println("Not valid.");
            return false;
        }else
        {
            a = operands.top();
            operands.pop();
        }
        char op= operators.top();
        operators.pop();
        float ans = binaryOperation(a,b,op);
        if(ans == Float.POSITIVE_INFINITY || ans == Float.NEGATIVE_INFINITY || ans == Float.NaN)
        {
            //System.out.println("Infinity case!"); //this is for dividing with 0
            System.out.println("Not valid.");
            return false;
        }
        operands.push(ans);
        return true;
    }

    public void calculation(String exp) // checks validity and calculates the expression
    {
        Stack<Float>operands = new Stack<>();
        Stack<Character>operators = new Stack<>();
        char prev='n';
        boolean digit = false; //tracker for multiple digit number
        boolean fraction = false; //tracker for fractional number
        float divider = (float) 10.0; //used to handle fractional number
        boolean unary = false; // tracker for unary operator (-)

        for(int i=0; i<exp.length(); i++)
        {
            char e = exp.charAt(i);

            if(isNumber(e))
            {
                float num = Float.parseFloat(String.valueOf(e));
                if(digit && fraction)
                {
                    float x = operands.top();
                    operands.pop();
                    num/=divider; //handling fractional number
                    divider*=10;
                    num+=x;
                }else if(digit) //handling integer number
                {
                    float x = operands.top();
                    operands.pop();
                    x=x*10;
                    num+=x;
                }
                operands.push(num);
                digit = true;
            }else if(isFraction(e))
            {
                if(fraction) //to handle 1.3.5 such cases!
                {
                    System.out.println("Not valid.");
                    return;
                }
                fraction = true;
            }else if(isOperator(e))
            {
                if(fraction && divider==10) //to handle 1.+4 such cases!
                {
                    System.out.println("Not valid.");
                    return;
                }
                digit = false;
                fraction = false;
                divider = (float) 10.0;
                if(e=='-' && prev == '(') //(-6) such case may occur
                {
                    unary = true;
                }
                while (!operators.isEmpty() && checkPrecedence(operators.top())>= checkPrecedence(e))
                {
                    if(!formattingOperation(operators, operands)) //if we can't format the operation then the expression is invalid!
                    {
                        return;
                    }

                }
                operators.push(e);
            }
            else if(e=='(')
            {
                if(fraction && divider==10) //to handle 4.(4+5) such cases!
                {
                    System.out.println("Not valid.");
                    return;
                }
                operators.push(e); //pushing "(" to operator stack
                digit = false; //to turn off the procedure for multiple digit number
                fraction = false; //to turn off the procedure for fraction number
                divider = (float) 10.0;
            }
            else if(e==')')
            {
                if(fraction && divider==10) //to handle "32.)" such cases!
                {
                    System.out.println("Not valid.");
                    return;
                }
                boolean flag = false; //to check whether the expression will be valid or not

                while (!operators.isEmpty())
                {
                    char op = operators.top();
                    if(op=='-' && unary) //then in the operator stack, there is ( and -
                    {
                        operators.pop(); //popping "-"
                        if(operators.isEmpty() || !digit) //if operator is empty then there wasn't enough operator, if digit is false then after "-" no digit occurred! to handle (-), 5+6(-) such cases!
                        {
                            System.out.println("Not valid.");
                            return;
                        }
                        char prevOp = operators.top();
                        if(prevOp=='(')
                        {
                            float num = operands.top();
                            operands.pop();
                            operators.pop();
                            num *= -1;
                            operands.push(num);
                            flag = true;
                            unary = false;
                            break;
                        }else
                        {
                            System.out.println("Not valid.");
                            return;
                        }
                    }
                    if(op=='(')
                    {
                        flag = true;
                        operators.pop();
                        unary = false;
                        break;
                    }
                    if(!formattingOperation(operators, operands))
                    {
                        return;
                    }

                    unary = false;
                }
                if(!flag)
                {
                    System.out.println("Not valid.");
                    return;
                }
                //setting these trackers to their initial condition
                digit = false;
                fraction = false;
                divider = (float) 10.0;
            }else if(e==' ')
            {
                //allowing white space between an operator and a digit to be valid
                digit = false;
                fraction = false;
                divider = (float) 10.0;
            }else{
                System.out.println("Not valid.");
                return;
            }
            prev = e;
        }

        while (!operators.isEmpty())
        {
            char op = operators.top();
            if(op=='(')
            {
                System.out.println("Not valid.");
                return;
            }
            if(!formattingOperation(operators, operands))
            {
                return;
            }
        }
        if(operands.isEmpty())
        {
            System.out.println("Not valid.");
            return;
        }
        float result = operands.top();
        operands.pop();
        if(!operands.isEmpty()) // then there are still operands left! to handle 5 6+4 such cases!
        {
            System.out.println("Not valid.");
            return;
        }
        System.out.println("Valid expression, Computed value: " + result);
    }
}
