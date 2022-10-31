package com.assignment.abnamro.dto.model;

import java.util.List;

public interface Recipe {

    Long getId();
    String getRecipeName();
    Integer getServingsNumber();
    List<Ingredient> getIngredients();
    String getInstructions();
}
