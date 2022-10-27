package com.assignment.abnamro.dto;

import com.assignment.abnamro.entity.RecipeEntity;
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

    private Long id;
    private String recipeName;
    private Integer servingsNumber;
    private List<IngredientsDTO> ingredients;
    private String instructions;

    public RecipeDTO toDTO(RecipeEntity recipeEntity) {

        return this.builder().
                recipeName(recipeEntity.getRecipeName()).
                servingsNumber(recipeEntity.getServingsNumber()).
                instructions(recipeEntity.getInstructions()).build();
    }
}
