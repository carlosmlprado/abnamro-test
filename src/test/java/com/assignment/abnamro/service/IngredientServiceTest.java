package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.entity.RecipeEntity;
import com.assignment.abnamro.exceptions.RecipesExceptions;
import com.assignment.abnamro.helpers.IngredientHelper;
import com.assignment.abnamro.helpers.RecipeHelper;
import com.assignment.abnamro.repository.IngredientRepository;
import com.assignment.abnamro.repository.RecipeRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    public void when_relating_ingredients_to_recipe_then_should_be_ok() {
        var ingredientHelper = new IngredientHelper();
        var recipeHelper = new RecipeHelper();
        when(ingredientRepository.saveAll(any())).thenReturn(ingredientHelper.createIngredientMockList());

        RecipeEntity recipeEntity = ingredientService.relateIngredientsForRecipe(ingredientHelper.createIngredientMockListDTO(), recipeHelper.createMockRecipe());

        assertEquals(Optional.ofNullable(recipeEntity.getIngredients().get(0).getIngredientId()), Optional.ofNullable(10L));
        assertEquals(Optional.ofNullable(recipeEntity.getIngredients().get(1).getIngredientId()), Optional.ofNullable(11L));

    }
}