package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.RecipeDTO;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private RecipeService recipeService;

    @InjectMocks
    private IngredientService ingredientService;

    @Test
    public void when_get_all_recipes_then_return_all_recipes_with_no_error() {
        var recipeHelper = new RecipeHelper();
        var recipeList = recipeHelper.createMockRecipeListEntity();
        when(recipeRepository.findAll()).thenReturn(recipeList);

        List<RecipeDTO> recipeListDTO = recipeService.getAllRecipes();

        assertEquals(recipeList.size(), 2);
        assertEquals(recipeList.get(0).getRecipeId(), recipeListDTO.get(0).getRecipeId());
        assertEquals(recipeList.get(1).getRecipeId(), recipeListDTO.get(1).getRecipeId());

        assertEquals(recipeList.get(0).getRecipeName(), recipeListDTO.get(0).getRecipeName());
        assertEquals(recipeList.get(1).getRecipeName(), recipeListDTO.get(1).getRecipeName());

        assertEquals(recipeList.get(0).getServingsNumber(), recipeListDTO.get(0).getServingsNumber());
        assertEquals(recipeList.get(1).getServingsNumber(), recipeListDTO.get(1).getServingsNumber());
    }

    @Test
    public void when_getting_existing_recipeId_then_return_recipe_dto() {

        var recipeHelper = new RecipeHelper();
        var recipe = recipeHelper.createMockRecipe();
        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipe));

        RecipeDTO recipeDTO = recipeService.getRecipeById(10L);

        assertEquals(recipe.getRecipeId(), recipeDTO.getRecipeId());
        assertEquals(recipe.getRecipeName(), recipeDTO.getRecipeName());
    }

    @Test
    public void when_getting_non_existing_recipeId_then_return_not_found_exception() {

        assertThrows(RecipesExceptions.class, () -> {
            recipeService.getRecipeById(10L);
        });

    }

    @Test
    public void when_updating_new_recipe_then_should_update_with_no_error() {

        var recipeHelper = new RecipeHelper();
        var ingredientHelper = new IngredientHelper();
        var recipe = recipeHelper.createMockRecipe();
        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipe));
        when(ingredientRepository.saveAll(ingredientHelper.createIngredientMockList())).thenReturn(ingredientHelper.createIngredientMockList());
        var recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeId(recipe.getRecipeId());

        recipeDTO.setRecipeName("new recipe name");
        recipeDTO.setInstructions("new instructions");

        recipeService.createUpdateRecipe(recipeDTO, true);

        assertNotEquals(recipe.getRecipeName(), "new recipe name");
        assertNotEquals(recipe.getInstructions(), "new instructions");

    }

    @Test
    public void when_delete_non_existing_recipeId_then_return_not_found_exception() {

        assertThrows(RecipesExceptions.class, () -> {
            recipeService.deleteRecipe(9L);
        });
    }

}