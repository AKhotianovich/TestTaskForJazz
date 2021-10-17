package com.company;

import java.util.Stack;

public class Calculator {

    public double decide(String expression){
        String rpn = getRpn(expression);
        System.out.println(rpn);
        return RPNtoAnswer(rpn);
    }

    public String getRpn(String expression){
        String preparied = preparingExpression(expression);
        String rpn = ExpressionToRPN(preparied);
        return rpn;
    }

    private String preparingExpression(String expression){
        StringBuilder preparingExpression = new StringBuilder();
        for (int token = 0; token < expression.length(); token++) {
            char symbol = expression.charAt(token);
            if (symbol == '-'){
                if (token == 0){
                    preparingExpression.append(0);
                }
                else if (expression.charAt(token - 1) == '('){
                    preparingExpression.append(0);
                }
            }
            preparingExpression.append(symbol);
        }
        return preparingExpression.toString();
    }

    private String ExpressionToRPN(String expression){
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            if(priority == 0) current.append(expression.charAt(i));
            if(priority == 1) stack.push(expression.charAt(i));

            if(priority > 1){
                current.append(' ');
                while (!stack.empty()){
                    if(getPriority(stack.peek()) >= priority) current.append(stack.pop());
                    else break;;
                }
                stack.push(expression.charAt(i));
            }

            if(priority == -1){
                current.append(' ');
                while(getPriority(stack.peek()) != 1){
                    current.append(stack.pop());
                }
                stack.pop();
            }
        }

        while (!stack.empty()) current.append(stack.pop());

        return current.toString();
    }

    private Double RPNtoAnswer(String rpn){
        StringBuilder operand = new StringBuilder();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if(rpn.charAt(i) == ' ') continue;

            if(getPriority(rpn.charAt(i)) == 0){
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0) {
                    operand.append(rpn.charAt(i++));
                    if (i == rpn.length()){
                        break;
                    }
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder();
            }

            if (getPriority(rpn.charAt(i)) > 1){
                double a = stack.pop(), b =stack.pop();

                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
            }
        }
        return stack.pop();
    }

    private int getPriority(char token){
        if (token == ')') return -1;
        else if (token == '(') return 1;
        else if (token == '+' || token == '-') return 2;
        else if (token == '*' || token == '/') return 3;
        else return 0;
    }
}
