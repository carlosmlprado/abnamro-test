package com.assignment.abnamro.dto;

import com.assignment.abnamro.enums.IngredientMeasurements;
import lombok.Data;

import java.io.Serializable;
@Data
public class IngredientsDTO implements Serializable {

    private String ingredientName;
    private Integer quantity;
    private IngredientMeasurements ingredientMeasurements;

}
