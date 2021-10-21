package com.company;

import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalculatorTest {

    private final String expression;
    private final Double answer;
    private final String rpnExpression;
    private Calculator calculator;

    @Before
    public void initialize() {
        calculator = new Calculator();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {

        final List<Object[]> result = new ArrayList<>();

        result.add(new Object[]{"2+2", 4.0, "2 2+"});
        result.add(new Object[]{"-(-2-2)", 4.0, "0 0 2 -2 --"});
        result.add(new Object[]{"3-2", 1.0, "3 2-"});
        result.add(new Object[]{"4*2", 8.0, "4 2*"});
        result.add(new Object[]{"2/2", 1.0, "2 2/"});
        result.add(new Object[]{"(2+2)*2", 8.0, "2 2 + 2*"});
        result.add(new Object[]{"-2+-2", 0.0, "0 2 - +2-"});
        result.add(new Object[]{"-2+-2a", 0.0, "Error"});
        result.add(new Object[]{"-2b+-2a", 0.0, "Error"});

        return result;
    }

    public CalculatorTest(String expression, Double answer, String rpnExpression) {
        this.expression = expression;
        this.answer = answer;
        this.rpnExpression = rpnExpression;

    }

    @Test
    public void decide() {
        System.out.printf(
                "running test with param expression = %s and answer = %s%n"
                , expression, answer);
        assertEquals(calculator.decide(expression), answer, 0.0001);
    }

    @Test
    public void ExpressionToRPN() {
        System.out.printf(
                "running test with param expression = %s and rpnExpression = %s%n"
                , expression, rpnExpression);
        assertEquals(calculator.getRpn(expression), rpnExpression);

    }
}