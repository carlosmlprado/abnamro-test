package com.assignment.abnamro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeType {

    OMNIVOROUS("omnivorous"),
    VEGETARIAN("vegetarian"),
    VEGAN("vegan");

    private final String recipeType;
}
