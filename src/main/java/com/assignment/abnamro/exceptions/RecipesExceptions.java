package com.assignment.abnamro.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
@NoArgsConstructor
public class RecipesExceptions extends RuntimeException {

    private String message;
}
