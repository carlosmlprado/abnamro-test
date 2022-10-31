//package com.assignment.abnamro.service;
//
//import com.assignment.abnamro.helpers.RecipeHelper;
//import com.assignment.abnamro.repository.RecipeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RecipeServiceTest {
//
//    @Mock
//    private RecipeRepository recipeRepository;
//
//    @InjectMocks
//    private RecipeService recipeService;
//
//    @Test
//    void when_get_all_recipes_then_return_all_recipes_with_no_error() {
//        var recipeHelper = new RecipeHelper();
//        var recipeList = recipeHelper.createMockRecipeListEntity();
//        when(recipeRepository.findAll()).thenReturn(recipeList);
//
//        var recipeListDTO = recipeHelper.createMockRecipeListDTO();
//
//        assertEquals(recipeList.size(), 2);
//        assertEquals(recipeList.get(0).getId(), recipeListDTO.get(0).getId());
//        assertEquals(recipeList.get(1).getId(), recipeListDTO.get(1).getId());
//
//        assertEquals(recipeList.get(0).getRecipeName(), recipeListDTO.get(0).getRecipeName());
//        assertEquals(recipeList.get(1).getRecipeName(), recipeListDTO.get(1).getRecipeName());
//
//        assertEquals(recipeList.get(0).getServingsNumber(), recipeListDTO.get(0).getServingsNumber());
//        assertEquals(recipeList.get(1).getServingsNumber(), recipeListDTO.get(1).getServingsNumber());
//
//    }
//
//    @Test
//    void getRecipeById() {
//    }
//
//    @Test
//    void createUpdateRecipe() {
//    }
//
//    @Test
//    void deleteRecipe() {
//    }
//
//
//}