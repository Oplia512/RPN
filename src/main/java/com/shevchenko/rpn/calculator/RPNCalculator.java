package com.shevchenko.rpn.calculator;

import com.shevchenko.rpn.calculator.core.Calculator;
import com.shevchenko.rpn.exception.WrongDataFormatException;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import static com.shevchenko.rpn.exception.ErrorMessage.*;


public class RPNCalculator implements Calculator {

    public static final String ADDITION = "+";
    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
    public static final String SEPARATOR = " ";

    private Stack<Double> numbers = new Stack<>();

    @Override
    public double calculate(Supplier<String> notationSupplier) throws WrongDataFormatException {
        String data = notationSupplier.get();
        if (!isExist(data)) {
            throw new IllegalArgumentException(ERR_MESSAGE_NO_DATA);
        }

        Arrays.stream(data.split(SEPARATOR)).forEach(character -> {
            switch (character) {
                case ADDITION:
                    applyOperator(numbers, (number1, number2) -> number1 + number2);
                    break;

                case SUBTRACTION:
                    applyOperator(numbers, (number1, number2) -> number2 - number1);
                    break;

                case MULTIPLICATION:
                    throw new UnsupportedOperationException(String.format(ERR_MESSAGE_OPERATION_NOT_SUPPORTED, character));

                case DIVISION:
                    throw new UnsupportedOperationException(String.format(ERR_MESSAGE_OPERATION_NOT_SUPPORTED, character));

                default:
                    try {
                        numbers.push(Double.valueOf(character));
                    } catch (NumberFormatException exception) {
                        throw new WrongDataFormatException(String.format(ERR_MESSAGE_WRONG_DATA_FORMAT, character));
                    }
            }
        });
        return numbers.pop();
    }

    private static void applyOperator(Stack<Double> stackOfNumbers, BiFunction<Double, Double, Double> operation) {
        stackOfNumbers.push(operation.apply(stackOfNumbers.pop(), stackOfNumbers.pop()));
    }
}
