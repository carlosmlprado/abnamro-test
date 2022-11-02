package com.assignment.abnamro.controller;

import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.exceptions.RecipesExceptions;
import com.assignment.abnamro.helpers.RecipeHelper;
import com.assignment.abnamro.repository.RecipeRepository;
import com.assignment.abnamro.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    void when_creating_recipe_then_should_return_created_recipe() throws Exception {
        var recipeHelper = new RecipeHelper();
        when(recipeService.createRecipe(recipeHelper.createMockRecipeDTO())).thenReturn(recipeHelper.createMockRecipeDTO());

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(recipeHelper.createMockRecipeDTO());

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.CREATED.value())).
                andExpect(jsonPath("$.recipeName").value("Feijoada")).
                andExpect(jsonPath("$.instructions").value("Dummy instruction"));

    }

    @Test
    void when_updating_recipe_then_should_return_updated_recipe() throws Exception {
        var recipeHelper = new RecipeHelper();
        var recipeDTO = recipeHelper.createMockRecipeDTO();
        when(recipeService.updateRecipe(recipeHelper.createMockRecipeDTO(), 1L)).thenReturn(recipeDTO);

        recipeDTO.setRecipeName("new recipe name");
        recipeDTO.setInstructions("new instructions");

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(recipeHelper.createMockRecipeDTO());

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + "/1")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).
                andExpect(status().is(HttpStatus.OK.value())).
                andExpect(jsonPath("$.recipeName").value("new recipe name")).
                andExpect(jsonPath("$.instructions").value("new instructions"));
    }

    @Test
    void when_getting_non_existing_recipeId_then_should_return_not_found() throws Exception {
        var recipeHelper = new RecipeHelper();
        when(recipeService.getRecipeById(11L)).thenReturn(recipeHelper.createMockRecipeDTO());

        MockHttpServletRequestBuilder request = get(BASE_URL + "/10");

        mockMvc.perform(request).andExpect(jsonPath("[0].recipeId").doesNotExist());

    }

    @Test
    void when_getting_existing_recipeId_then_should_return_recipe() throws Exception {
        var recipeHelper = new RecipeHelper();
        when(recipeService.getRecipeById(1L)).thenReturn(recipeHelper.createMockRecipeDTO());

        MockHttpServletRequestBuilder request = get(BASE_URL + "/1");

        mockMvc.perform(request).
                andExpect(jsonPath("$.recipeId").value(1L)).
                andExpect(status().isOk());
    }

    @Test
    void when_deleting_existing_recipe_then_should_return_no_content() throws Exception {
        MockHttpServletRequestBuilder request = delete(BASE_URL + "/{recipeId}", 1L);

        mockMvc.perform(request)
                .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    void when_deleting_non_existing_recipe_then_should_throw_not_found_exception() throws Exception {
        doThrow(RecipesExceptions.class).when(recipeService).deleteRecipe(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/1")).
                andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void when_filtering_recipes_then_should_return_ok_response() throws Exception {
        var recipeFilterDTO = new RecipeFilterDTO();
        recipeFilterDTO.setInstructionsSearch("instructions");
        var recipeHelper = new RecipeHelper();
        when(recipeService.getFilteredRecipes(recipeFilterDTO)).thenReturn(recipeHelper.createMockRecipeListDTO());

        MockHttpServletRequestBuilder request = get(BASE_URL + "/filter?textSearch=cook");

        mockMvc.perform(request).
                andExpect(status().isOk());

    }
}