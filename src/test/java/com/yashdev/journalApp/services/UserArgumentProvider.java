package com.yashdev.journalApp.services;
import com.yashdev.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("Sita").password("Sita").build()),
                Arguments.of(User.builder().userName("Suraj").password("Suraj").build())
        );
    }
}
