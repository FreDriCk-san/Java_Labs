package com.company;

import java.util.ArrayList;
import java.util.Stack;

class Calculator {

    static double calculate(String expression) {

        if (expression == null)
        {
            throw new NullPointerException("Current expression does not exist");
        }

        if (expression.length() == 0)
        {
            return 0;
        }

        if (!correctBrackets(expression))
        {
            throw new RuntimeException("Incorrect form of brackets!");
        }

        if (repeatingSymbols(expression))
        {
            throw new RuntimeException("Syntax error!");
        }

        Stack<Character> stack = new Stack<>();

        ArrayList<String> postfixList = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char anInfix = expression.charAt(i);

            if (Character.isDigit(anInfix)) {
                int counter = 0;
                while (i + counter != expression.length() && Character.isDigit(expression.charAt(i + counter)))
                {
                    counter++;
                }
                postfixList.add(expression.substring(i, i+counter));
                i += counter - 1;
            }

            if (anInfix == '(') {
                stack.add(anInfix);
            }
            else if (anInfix == '*' || anInfix == '/') {
                if (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/')) {

                    while (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        postfixList.add(stack.pop().toString());
                    }
                }
                stack.add(anInfix);
            }
            else if (anInfix == '+' || anInfix == '-') {
                if (!stack.empty() && (stack.peek() == '+' || stack.peek() == '-' ||
                        stack.peek() == '/' || stack.peek() == '*')) {
                    while (!stack.empty() && (stack.peek() == '+' || stack.peek() == '-' ||
                            stack.peek() == '/' || stack.peek() == '*')) {
                        postfixList.add(stack.pop().toString());
                    }
                }
                stack.add(anInfix);
            }
            else if (anInfix == ')') {
                while (stack.peek() != '(') {
                    postfixList.add(stack.pop().toString());
                }
                stack.pop();
            }

        }

        while (!stack.empty()) {
            stack.peek();
            postfixList.add(stack.pop().toString());
        }

        return postfix(postfixList);
    }


    private static double postfix(ArrayList<String> expression) {

        Stack<Double> stack = new Stack<>();

        for (String i: expression)
        {

            if (isNumber(i))
            {
                stack.push(Double.parseDouble(i));
            }
            else if (stack.size() < 2)
            {
                return Double.NaN;
            }
            else
            {
                double secondPop = stack.pop();
                switch (i)
                {
                    case "+" :
                        stack.push(stack.pop() + secondPop);
                        break;

                    case "-" :
                        stack.push(stack.pop() - secondPop);
                        break;

                    case "/" :
                        try {
                            stack.push(stack.pop() / secondPop);
                        }
                        catch (ArithmeticException e)
                        {
                            System.err.println(e);
                            System.err.println("Cause: " + e.getCause());

                            return Double.NaN;
                        }
                        break;

                    case "*" :
                        stack.push(stack.pop() * secondPop);
                        break;
                }
            }
        }

        return stack.pop();
    }

    private static boolean isNumber (String expression)
    {
        try {
            Double.parseDouble(expression);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    private static boolean correctBrackets(String expression) {
        int counter = 0;
        for (int i = 0; i < expression.length(); i++)
        {
            if (expression.charAt(i) == '(')
            {
                counter++;
            }
            else if (expression.charAt(i) == ')')
            {
                counter--;
            }
            if (counter < 0)
            {
                break;
            }
        }
        return ( counter == 0);
    }

    private static boolean repeatingSymbols(String expression) {

        for (int i = 0; i < expression.length(); i++)
        {
            if ((expression.charAt(i) == '+'
                    || expression.charAt(i) == '-'
                    || expression.charAt(i) == '*'
                    || expression.charAt(i) == '/')
                    && (expression.charAt(i) == expression.charAt(i+1)
                    || expression.charAt(i) == expression.charAt(i+2)))
            {
                return true;
            }
        }
        return false;
    }

}