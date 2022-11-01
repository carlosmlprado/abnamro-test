package com.assignment.abnamro.controller;

import io.restassured.response.Response;
import com.assignment.abnamro.helpers.RecipeHelper;
import com.assignment.abnamro.repository.RecipeRepository;
import com.assignment.abnamro.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    public static final String BASE_URL = "http://localhost:8080/api/recipes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    RecipeService recipeService;

    @Test
    void getAllRecipes() {


    }

    @Test
    void getRecipeById() {
    }

    @Test
    void getRecipeByFilter() {
    }

    @Test
    void createRecipe() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void when_deleting_non_existing_recipe_then_should_throw_not_found_exception() throws Exception {
        when(recipeRepository.findById(10L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/delete/10")).
                andExpect(status().isNotFound());
    }

    @Test
    void when_deleting_existing_recipe_then_should_return_no_content() throws Exception {


        var recipeHelper = new RecipeHelper();
        when(recipeRepository.findById(10L)).thenReturn(Optional.of(recipeHelper.createMockRecipe()));

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/delete/10")).
                andExpect(status().isNoContent());
    }
}