package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.entity.RecipeEntity;
import com.assignment.abnamro.exceptions.ResourceNotFoundException;
import com.assignment.abnamro.helpers.IngredientHelper;
import com.assignment.abnamro.helpers.RecipeHelper;
import com.assignment.abnamro.repository.IngredientRepository;
import com.assignment.abnamro.repository.RecipeRepository;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private IngredientService ingredientService;

    @Captor
    private ArgumentCaptor<RecipeEntity> recipeEntityArgumentCaptor;

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

        assertThrows(ResourceNotFoundException.class, () -> {
            recipeService.getRecipeById(10L);
        });

    }

    @Test
    public void when_updating_new_recipe_then_should_update_with_no_error() {

        var recipeHelper = new RecipeHelper();
        var ingredientHelper = new IngredientHelper();
        var recipe = recipeHelper.createMockRecipe();
        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipe));
        when(ingredientService.relateIngredientsForRecipe(ingredientHelper.createIngredientMockListDTO(), recipe)).thenReturn(recipe);
        when(recipeRepository.save(any())).thenReturn(recipe);

        var recipeDTO = recipeHelper.createMockRecipeDTO();
        recipeDTO.setRecipeId(recipe.getRecipeId());

        recipeDTO.setRecipeName("new recipe name");
        recipeDTO.setInstructions("new instructions");
        recipeDTO.setIngredients(ingredientHelper.createIngredientMockListDTO());

        recipeService.updateRecipe(recipeDTO, 10L);

        verify(recipeRepository, times(1)).save(recipeEntityArgumentCaptor.capture());

    }

    @Test
    public void when_creating_new_recipe_then_should_create_with_no_error() {

        var recipeHelper = new RecipeHelper();
        var ingredientHelper = new IngredientHelper();
        var recipe = recipeHelper.createMockRecipe();
        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipe));
        when(ingredientService.relateIngredientsForRecipe(ingredientHelper.createIngredientMockListDTO(), recipe)).thenReturn(recipe);
        when(recipeRepository.save(any())).thenReturn(recipe);

        var recipeDTO = recipeHelper.createMockRecipeDTO();
        recipeDTO.setRecipeId(recipe.getRecipeId());

        recipeDTO.setRecipeName("new recipe name");
        recipeDTO.setInstructions("new instructions");
        recipeDTO.setIngredients(ingredientHelper.createIngredientMockListDTO());

        recipeService.createRecipe(recipeDTO);

        verify(recipeRepository, times(1)).save(recipeEntityArgumentCaptor.capture());

    }

    @Test
    public void when_delete_non_existing_recipeId_then_return_not_found_exception() {

        assertThrows(ResourceNotFoundException.class, () -> {
            recipeService.deleteRecipeById(9L);
        });
    }

    @Test
    public void when_get_all_recipes_with_filter_then_return_all_recipes_filtered_with_no_error() {
        var recipeFilterDTO = new RecipeFilterDTO();
        List<String> excludedIngredient = Arrays.asList("ketchup");
        recipeFilterDTO.setExcludedIngredient(excludedIngredient);
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        var ingredientHelper = new IngredientHelper();
        when(recipeRepository.findAll(ArgumentMatchers.<Specification<RecipeEntity>>any(), eq(pageable))).thenReturn(createPage());
        when(ingredientRepository.findByRecipeAndIngredientNameIn(any(),excludedIngredient)).thenReturn(Arrays.asList(ingredientHelper.createIngredientMock()));

        List<RecipeDTO> recipeListDTO = recipeService.getFilteredRecipes(recipeFilterDTO);

        assertEquals(recipeListDTO.size(), 1);
    }

    private Page<RecipeEntity> createPage(){
        var recipeHelper = new RecipeHelper();
        List<RecipeEntity> recipeEntities = recipeHelper.createMockRecipeListEntity();
        return new PageImpl<>(recipeEntities);
    }

}