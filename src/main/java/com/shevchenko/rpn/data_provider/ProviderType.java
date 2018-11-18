package com.shevchenko.rpn.data_provider;

import com.shevchenko.rpn.exception.UnknownTypeException;

import java.util.Arrays;

import static com.shevchenko.rpn.exception.ErrorMessage.ERR_MESSAGE_NO_PROVIDER;

public enum ProviderType {
    STRING((stringData) -> stringData);


    private Provider provider;

    ProviderType(Provider provider) {
        this.provider = provider;
    }

    public static Provider getProvider(String typeName) throws UnknownTypeException {
        if (isNotExist(typeName)) {
            throw new IllegalArgumentException(ERR_MESSAGE_NO_PROVIDER);
        }
        return Arrays.stream(ProviderType.values())
                .filter(type -> type.name().equals(typeName.toUpperCase()))
                .findFirst()
                .orElseThrow(UnknownTypeException::new).provider;
    }

    private static boolean isNotExist(String typeName) {
        return typeName == null || typeName.isEmpty();
    }
}
