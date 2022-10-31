package com.assignment.abnamro.dto;

import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.enums.IngredientMeasurements;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO implements Serializable {

    private Long id;
    private String ingredientName;
    private Integer quantity;
    private String ingredientMeasurements;
    private Long recipeId;

    public IngredientDTO toDTO(IngredientEntity ingredientEntity) {

        return IngredientDTO.builder().
                id(ingredientEntity.getId()).
                ingredientName(ingredientEntity.getIngredientName()).
                quantity(ingredientEntity.getQuantity()).
                ingredientMeasurements(ingredientEntity.getIngredientMeasurement()).
                recipeId(ingredientEntity.getRecipe().getId()).build();

    }

}
