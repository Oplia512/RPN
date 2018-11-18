package com.shevchenko.rpn;

import com.shevchenko.rpn.calculator.RPNCalculator;
import com.shevchenko.rpn.calculator.core.Calculator;
import com.shevchenko.rpn.config.AppConfig;
import com.shevchenko.rpn.data_provider.Provider;
import com.shevchenko.rpn.data_provider.ProviderType;
import com.shevchenko.rpn.exception.UnknownTypeException;
import com.shevchenko.rpn.exception.WrongDataFormatException;
import com.shevchenko.rpn.printer.Printable;
import com.shevchenko.rpn.printer.ResultPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

import static com.shevchenko.rpn.exception.ErrorMessage.ERR_MESSAGE_NO_NOTATION;

public class CalculatorApp {
    private final static Logger LOGGER = LoggerFactory.getLogger("CalculatorApp");

    public static void main(String[] args) {
        try {
            AppConfig appConfig = new AppConfig(new Properties());
            appConfig.parseProperties();

            Calculator calculator = new RPNCalculator();

            if (args.length == 0) {
                throw new IllegalArgumentException(ERR_MESSAGE_NO_NOTATION);
            }

            Provider provider = ProviderType.getProvider(appConfig.getProviderType());
            double result = calculator.calculate(() -> provider.getNotation(args[0]));

            Printable resultPrinter = ResultPrinter.SYSTEM_OUT;
            resultPrinter.print("Result is " + result);

        } catch (WrongDataFormatException | UnknownTypeException | IllegalArgumentException | IOException exception) {
            logException(exception);
        }
    }

    private static void logException(Exception exception) {
        LOGGER.error("Application failed, reason: {}", exception.getMessage(), exception);
    }
}
