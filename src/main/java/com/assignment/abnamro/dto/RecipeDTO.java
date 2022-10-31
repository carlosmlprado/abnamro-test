package com.assignment.abnamro.dto;

import com.assignment.abnamro.entity.RecipeEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO implements Serializable {

    private Long id;
    private String recipeName;
    private Integer servingsNumber;
    private List<IngredientDTO> ingredients;
    private String instructions;

    public RecipeDTO toDTO(RecipeEntity recipeEntity) {

        return RecipeDTO.builder().
                id(recipeEntity.getId()).
                recipeName(recipeEntity.getRecipeName()).
                servingsNumber(recipeEntity.getServingsNumber()).
                instructions(recipeEntity.getInstructions()).build();
    }
}
