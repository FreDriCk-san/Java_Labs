package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void postfixExpression() throws Exception {
        String expression = "(8 + 2 * 5) / (1 + 3 * 2 - 4)";
        assertEquals(6.0, Calculator.calculate(expression), 0.0);
    }

    @Test
    public void incorrectFormOfBrackets() throws Exception {
        String expression = "8 + 2 * 5)) / (1 + 3 * 2 - 4)";
        try {
            assertEquals(true, Calculator.calculate(expression));
            Assert.fail("Syntax error!");
        } catch (RuntimeException e) {

        }

    }

    @Test
    public void invalidSyntax() throws Exception {
        String expression = "(8 + + 2 * 5) / /(1 + 3 * 2 - 4)";
        try {
            assertEquals(true, Calculator.calculate(expression));
            Assert.fail("Incorrect form of brackets!");
        } catch (RuntimeException e) {
        }
    }

    @Test
    public void correctAddition() throws Exception {
        String expression = "2 + 2";
        assertEquals(4.0, Calculator.calculate(expression), 0.0);
    }

    @Test
    public void correctSubtraction() throws Exception {
        String expression = "6 - 2";
        assertEquals(4.0, Calculator.calculate(expression), 0.0);
    }

    @Test
    public void correctDivision() throws Exception {
        String expression = "4 / 2";
        assertEquals(2.0, Calculator.calculate(expression), 0.0);
    }

    @Test
    public void correctMultiplication() throws Exception {
        String expression = "8 * 2";
        assertEquals(16.0, Calculator.calculate(expression), 0.0);
    }

    @Test
    public void incorrectCharacters() throws Exception {
        try {
             Calculator.calculate( "EFSUYFGewuyhfguwyrfwufwyfwihfeie");
            Assert.fail("Incorrect characters");
        } catch (RuntimeException e) {}
    }
}