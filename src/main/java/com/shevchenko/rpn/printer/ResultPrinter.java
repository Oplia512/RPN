package com.shevchenko.rpn.printer;

import java.util.function.Consumer;

public enum ResultPrinter implements Printable {

    SYSTEM_OUT(System.out::println);

    private Consumer<String> stringConsumer;

    ResultPrinter(Consumer<String> stringConsumer) {
        this.stringConsumer = stringConsumer;
    }

    @Override
    public void print(String data) {
        stringConsumer.accept(data);
    }
}
