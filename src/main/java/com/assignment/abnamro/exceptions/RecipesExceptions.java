package com.assignment.abnamro.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RecipesExceptions extends RuntimeException {

    private String message;
}
