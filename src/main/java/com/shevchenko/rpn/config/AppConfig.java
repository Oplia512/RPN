package com.shevchenko.rpn.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.shevchenko.rpn.exception.ErrorMessage.ERR_MESSAGE_NO_PROPERTY_FILE;

public class AppConfig {

    private static final String PROPERTIES_FILE = "config/application.properties";
    private static final String PROVIDER_TYPE_PROPERTY = "rpn.provider.type";

    private final Properties prop;

    public AppConfig(Properties prop) {
        this.prop = prop;
    }

    public void parseProperties() throws IOException {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {

            if (input == null) {
                throw new FileNotFoundException(ERR_MESSAGE_NO_PROPERTY_FILE + PROPERTIES_FILE);
            }

            prop.load(input);
        }
    }

    public String getProviderType() {
        return prop.getProperty(PROVIDER_TYPE_PROPERTY);
    }
}
