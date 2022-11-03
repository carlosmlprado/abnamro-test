package com.assignment.abnamro.controller;


import com.assignment.abnamro.helpers.RecipeHelper;
import com.assignment.abnamro.repository.IngredientRepository;
import com.assignment.abnamro.repository.RecipeRepository;
import com.assignment.abnamro.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Optional;

import static com.jayway.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RecipeControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private RecipeService recipeService;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void when_getting_all_recipes_should_return_ok_status_code() {
        given().port(port).when().get("/api/recipes").then().statusCode(200);
    }

    @Test
    public void when_getting_all_recipes_with_given_filter_should_return_ok_status_code() {
        given().port(port).when().get("/api/recipes/filter?textSearch=cook&typeOfDiet=VEGAN&servingsNumber=6&includedIngredient=rice&excludedIngredient=bacon").then().statusCode(200);
    }

    @Test
    public void when_getting_recipe_by_existing_id_should_return_ok_status_code() {
        var recipeHelper = new RecipeHelper();
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipeHelper.createMockRecipe()));
        given().port(port).when().get("/api/recipes/1").then().statusCode(200);
    }

    @Test
    public void when_deleting_recipe_by_existing_id_should_return_no_content() {
        var recipeHelper = new RecipeHelper();
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipeHelper.createMockRecipe()));
        given().port(port).when().delete("/api/recipes/1").then().statusCode(204);
    }

    @Test
    public void when_creating_new_recipe_then_should_return_created_status_code() throws JsonProcessingException {
        var recipeHelper = new RecipeHelper();
        when(recipeService.createRecipe(recipeHelper.createMockRecipeDTO())).thenReturn(recipeHelper.createMockRecipeDTO());

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(recipeHelper.createMockRecipeDTO());

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/api/recipes")
                .then()
                .extract().response();

        response.then().statusCode(201).assertThat().body("recipeName",
                equalTo("Feijoada"));
    }

    @Test
    public void when_updating_recipe_with_existing_recipeId_then_should_return_ok_status_code() throws JsonProcessingException {
        var recipeHelper = new RecipeHelper();
        when(recipeService.createRecipe(recipeHelper.createMockRecipeDTO())).thenReturn(recipeHelper.createMockRecipeDTO());
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipeHelper.createMockRecipe()));

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(recipeHelper.createMockRecipeDTO());

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .put("/api/recipes/1")
                .then()
                .extract().response();

        response.then().statusCode(200);
    }

}