package com.assignment.abnamro.dto;

import com.assignment.abnamro.entity.RecipeEntity;
import com.assignment.abnamro.enums.RecipeType;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO implements Serializable {

    private Long recipeId;
    private String recipeName;
    private RecipeType typeOfDiet;
    private Integer servingsNumber;
    private List<IngredientDTO> ingredients;
    private String instructions;

    public RecipeDTO toDTO(RecipeEntity recipeEntity) {

        var ingredientDTO = new IngredientDTO();
        return RecipeDTO.builder().
                recipeId(recipeEntity.getRecipeId()).
                recipeName(recipeEntity.getRecipeName()).
                servingsNumber(recipeEntity.getServingsNumber()).
                ingredients(recipeEntity.getIngredients() != null ? recipeEntity.getIngredients().stream().map(ingredientDTO::toDTO).collect(Collectors.toList()) : null).
                instructions(recipeEntity.getInstructions()).
                typeOfDiet(RecipeType.valueOf(recipeEntity.getTypeOfDiet())).build();
    }
}
