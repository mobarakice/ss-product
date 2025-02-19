package com.sweetsavvy.app.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CustomScalarConfig {
    @Bean
    public RuntimeWiringConfigurer customScalarConfigurer() {
        return wiringBuilder -> wiringBuilder
            .scalar(ExtendedScalars.Date)  // Adding DateTime scalar type
            .scalar(ExtendedScalars.GraphQLLong); // Support for Long type
    }
}
