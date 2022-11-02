package com.assignment.abnamro.helpers;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.entity.RecipeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeHelper {

    public List<RecipeDTO> createMockRecipeListDTO() {

        var recipeListDTO = new ArrayList<RecipeDTO>();

        var recipe1 = new RecipeDTO();
        recipe1 = recipe1.builder().
                recipeId(1L).
                recipeName("Feijoada").
                servingsNumber(10).
                instructions("Dummy instruction cook").build();

        recipeListDTO.add(recipe1);

        var recipe2 = new RecipeDTO();
        recipe2 = recipe1.builder().
                recipeId(2L).
                recipeName("Vegan Feijoada").
                servingsNumber(10).
                instructions("Dummy instruction but take off all the meat").build();

        recipeListDTO.add(recipe2);

        return recipeListDTO;
    }

    public RecipeDTO createMockRecipeDTO() {

        var recipe1 = new RecipeDTO();
        recipe1 = recipe1.builder().
                recipeId(1L).
                recipeName("Feijoada").
                servingsNumber(10).
                instructions("Dummy instruction").build();

        return recipe1;
    }

    public List<RecipeEntity> createMockRecipeListEntity() {

        var recipeListEntity = new ArrayList<RecipeEntity>();
        var ingredientHelper = new IngredientHelper();

        var recipe1 = new RecipeEntity();
        recipe1 = recipe1.builder().
                recipeId(1L).
                recipeName("Feijoada").
                servingsNumber(10).
                instructions("instruction").
                ingredients(ingredientHelper.createIngredientMockList())
                .build();

        recipeListEntity.add(recipe1);

        var recipe2 = new RecipeEntity();
        recipe2 = recipe1.builder().
                recipeId(2L).
                recipeName("Vegan Feijoada").
                servingsNumber(10).
                instructions("dummy instruction but take off all the meat").build();

        recipeListEntity.add(recipe2);

        return recipeListEntity;
    }

    public RecipeEntity createMockRecipe() {

        var recipe1 = new RecipeEntity();
        recipe1 = recipe1.builder().
                recipeId(1L).
                recipeName("Feijoada").
                servingsNumber(10).
                instructions("Dummy instruction").build();

        return recipe1;
    }
}
