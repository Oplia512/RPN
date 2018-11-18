package com.shevchenko;

import com.shevchenko.rpn.calculator.RPNCalculator;
import com.shevchenko.rpn.calculator.core.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * Unit test for simple CalculatorApp.
 */

public class RPNCalculatorTest {

    private Calculator calculator;

    @BeforeClass
    public void prepare(){
        calculator = new RPNCalculator();
    }

    @DataProvider(name = "notations")
    public static Object[][] notations() {
        return new Object[][]{
                {"1 2 3 + +", 6.0},
                {"1 2 3 - +", 0.0},
                {"1 2 3 - -", 2.0}
        };

    }

    @Test(dataProvider = "notations")
    public void testCalculator(String notation, Double result) {
       Assert.assertEquals(calculator.calculate(() -> notation), result);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testCalculatorWitMultiplicationWillThrowException() {
        calculator.calculate(() -> "1 2 3 - *");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void testCalculatorWithDivisionWillThrowException() {
        calculator.calculate(() -> "1 2 3 - /");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculatorWithEmptyDataThrowException() {
        calculator.calculate(() -> "");
    }

}
