package com.assignment.abnamro.service;

import com.assignment.abnamro.helpers.RecipeHelper;
import com.assignment.abnamro.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class RecipeServiceTest {

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    void getAll() {
        var recipeHelper = new RecipeHelper();
        var recipeList = recipeHelper.createMockRecipeListEntity();
        when(recipeRepository.findAll()).thenReturn(recipeList);

        assertEquals(recipeList.size(), 2);
        assertEquals(Optional.ofNullable(recipeList.get(0).getId()), 1L);
        assertEquals(Optional.ofNullable(recipeList.get(1).getId()), 2L);

        assertEquals(recipeList.get(0).getRecipeName(), "Feijoada");
        assertEquals(recipeList.get(1).getRecipeName(), "Vegan Feijoada");

        assertEquals(Optional.ofNullable(recipeList.get(0).getServingsNumber()), 10);
        assertEquals(Optional.ofNullable(recipeList.get(1).getServingsNumber()), 10);

    }

    @Test
    void getRecipeById() {
    }

    @Test
    void createUpdateRecipe() {
    }

    @Test
    void deleteRecipe() {
    }


}