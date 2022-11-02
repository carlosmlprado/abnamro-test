//package com.assignment.abnamro.service;
//
//import com.assignment.abnamro.entity.RecipeEntity;
//import com.assignment.abnamro.helpers.IngredientHelper;
//import com.assignment.abnamro.helpers.RecipeHelper;
//import com.assignment.abnamro.repository.IngredientRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(MockitoJUnitRunner.class)
//class IngredientServiceTest {
//
//    @Mock
//    private IngredientRepository ingredientRepository;
//
//    @InjectMocks
//    private RecipeService recipeService;
//
//    @InjectMocks
//    private IngredientService ingredientService;
//
//    @Test
//    public void relateIngredientsForRecipe() {
//        var ingredientHelper = new IngredientHelper();
//        var recipeHelper = new RecipeHelper();
//        when(ingredientRepository.saveAll(any())).thenReturn(ingredientHelper.createIngredientMockList());
//
//        RecipeEntity recipeEntity = ingredientService.relateIngredientsForRecipe(ingredientHelper.createIngredientMockListDTO(), recipeHelper.createMockRecipe());
//
//        assertEquals(recipeEntity.getIngredients().get(0).getIngredientId(), 10L);
//
//
//    }
//}