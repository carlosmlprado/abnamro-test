package com.assignment.abnamro.controller;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/recipes")
@RestController
@AllArgsConstructor
@Api(value = "Recipes")
public class RecipeController {

    private RecipeService recipesService;

    @GetMapping
    @ApiOperation(value = "Show all recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipesService.getAllRecipes());
    }

    @GetMapping("/{recipeId}")
    @ApiOperation(value = "Show recipe by Id")
    public ResponseEntity<RecipeDTO> getRecipeById(@NonNull @PathVariable Long recipeId) {
        return ResponseEntity.ok(recipesService.getRecipeById(recipeId));
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Get recipe by given filters")
    public ResponseEntity<List<RecipeDTO>> getRecipeByFilter(String typeOfDiet, Integer numberOfServings,
                                                             List<String> includedIngredient, List<String> excludedIngredient,
                                                             String textSearch) {
        RecipeFilterDTO recipeFilterDTO = new RecipeFilterDTO().builder(typeOfDiet, numberOfServings, includedIngredient, excludedIngredient, textSearch);
        
        return ResponseEntity.ok(recipesService.getFilteredRecipes(recipeFilterDTO));
    }

    @PostMapping
    @ApiOperation(value = "Create recipe")
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        var recipe = recipesService.createUpdateRecipe(recipeDTO, false);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation(value = "Update recipe")
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO) {
        var recipe = recipesService.createUpdateRecipe(recipeDTO, true);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    @ApiOperation(value = "Delete recipe by Id")
    public ResponseEntity<Void> deleteRecipeById(@NonNull @PathVariable Long recipeId) {
        recipesService.deleteRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
