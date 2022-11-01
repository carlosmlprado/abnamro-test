package com.assignment.abnamro.dto;

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
public class RecipeFilterDTO implements Serializable {

    private String typeOfDiet;
    private Integer numberOfServings;
    private List<String> includedIngredient;
    private List<String> excludedIngredient;
    private String instructionsSearch;

    public static RecipeFilterDTO toDTO(String typeOfDiet, Integer numberOfServings,
                                   List<String> includedIngredients, List<String> excludedIngredients,
                                   String textSearch) {

        return RecipeFilterDTO.builder().
                typeOfDiet(typeOfDiet).
                numberOfServings(numberOfServings).
                includedIngredient(includedIngredients).
                excludedIngredient(excludedIngredients).
                instructionsSearch(textSearch).build();
    }
}
