package com.company;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = "(-2)+(-2)+2*2";
        expression.replaceAll("\\s","");
        System.out.println(calculator.getRpn(expression));
        System.out.println(calculator.decide(expression));


    }
}
