package com.company;

import com.sun.source.tree.AssertTree;
import org.junit.Test;
import org.junit.Assert;


public class CalculatorTest {
    @Test
    public void decide() {
        Assert.assertEquals(4.0, new Calculator().decide("2+2"), 0.0001);
        Assert.assertEquals(4.0, new Calculator().decide("-(-2-2)"), 0.0001);
        Assert.assertEquals(1.0, new Calculator().decide("3-2"), 0.0001);
        Assert.assertEquals(8.0, new Calculator().decide("4*2"), 0.0001);
        Assert.assertEquals(1.0, new Calculator().decide("2/2"), 0.0001);
        Assert.assertEquals(8.0, new Calculator().decide("(2+2)*2"), 0.0001);

    }

    @Test
    public void ExpressionToRPN(){
        Assert.assertEquals(new Calculator().getRpn("2+2"), "2 2+");
        Assert.assertEquals(new Calculator().getRpn("-(-2-2)"), "0 0 2 -2 --");
        Assert.assertEquals(new Calculator().getRpn("3-2"), "3 2-");
        Assert.assertEquals(new Calculator().getRpn("4*2"), "4 2*");
        Assert.assertEquals(new Calculator().getRpn("2/2"), "2 2/");
        Assert.assertEquals(new Calculator().getRpn("(2+2)*2"), "2 2 + 2*");
    }
}