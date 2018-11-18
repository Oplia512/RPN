package com.shevchenko.rpn.calculator.core;

import com.shevchenko.rpn.exception.WrongDataFormatException;

import java.util.function.Supplier;

@FunctionalInterface
public interface Calculator {
    double calculate(Supplier<String> notationSupplier) throws WrongDataFormatException;

    default boolean isExist(String string){
        return string != null && !string.isEmpty();
    }
}
