package com.shevchenko.rpn.data_provider;

@FunctionalInterface
public interface Provider {
    String getNotation(String source);
}
