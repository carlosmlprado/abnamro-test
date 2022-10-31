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

    public RecipeFilterDTO builder(String typeOfDiet, Integer numberOfServings,
                                   List<String> includedIngredient, List<String> excludedIngredient, String textSearch) {

        return RecipeFilterDTO.builder().
                typeOfDiet(typeOfDiet).
                numberOfServings(numberOfServings).
                includedIngredient(includedIngredient).
                excludedIngredient(excludedIngredient)
                .instructionsSearch(textSearch).build();
    }
}
