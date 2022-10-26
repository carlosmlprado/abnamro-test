package com.assignment.abnamro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IngredientMeasurements {

    TABLESPOON("tableSpoon"),
    TEASPOON("teaSpoon"),
    CUP("cup");

    private final String ingredientMeasures;
}
