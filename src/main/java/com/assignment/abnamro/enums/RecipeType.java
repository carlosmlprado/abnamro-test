package com.assignment.abnamro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeType {

    OMNIVOROUS("OMNIVOROUS"),
    VEGETARIAN("VEGETARIAN"),
    VEGAN("VEGAN");

    private final String recipeType;
}
