package com.assignment.abnamro.dto;

import com.assignment.abnamro.entity.RecipesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO implements Serializable {

    private String recipeName;
    private Integer servingsNumber;
    private List<IngredientsDTO> ingredients;
    private String instructions;

    public RecipeDTO toDTO(RecipesEntity recipeEntity) {

        return this.builder().
                recipeName(recipeEntity.getRecipeName()).
                servingsNumber(recipeEntity.getServingsNumber()).
                instructions(recipeEntity.getInstructions()).build();
    }
}
