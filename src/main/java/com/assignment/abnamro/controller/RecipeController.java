package com.assignment.abnamro.controller;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Show all recipes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipes returned."),
            @ApiResponse(code = 500, message = "Exception from server"),
    })
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        return ResponseEntity.ok(recipesService.getAllRecipes());
    }

    @ApiOperation(value = "Get recipes by recipe Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipe returned"),
            @ApiResponse(code = 404, message = "Recipe Not Found."),
            @ApiResponse(code = 500, message = "Exception from server"),
    })
    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipeById(@NonNull @PathVariable Long recipeId) {
        return ResponseEntity.ok(recipesService.getRecipeById(recipeId));
    }

    @ApiOperation(value = "Get recipes by given filters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipes returned."),
            @ApiResponse(code = 500, message = "Exception from server"),
    })
    @GetMapping("/filter")
    public ResponseEntity<List<RecipeDTO>> getRecipeByFilter(@RequestParam(required = false) String typeOfDiet,
                                                             @RequestParam(required = false) Integer servingsNumber,
                                                             @RequestParam(required = false) List<String> includedIngredient,
                                                             @RequestParam(required = false) List<String> excludedIngredient,
                                                             @RequestParam(required = false) String textSearch) {
        RecipeFilterDTO recipeFilterDTO = RecipeFilterDTO.toDTO(typeOfDiet, servingsNumber, includedIngredient, excludedIngredient, textSearch);

        return ResponseEntity.ok(recipesService.getFilteredRecipes(recipeFilterDTO));
    }

    @ApiOperation(value = "Create a new Recipe.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Recipe created"),
            @ApiResponse(code = 400, message = "Bad Request. Review Your Object."),
            @ApiResponse(code = 500, message = "Exception from server"),
    })
    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO) {
        var recipe = recipesService.createRecipe(recipeDTO);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update existing Recipe by recipeId.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipe updated"),
            @ApiResponse(code = 400, message = "Bad Request. Review Your Object."),
            @ApiResponse(code = 500, message = "Exception from server"),
    })
    @PutMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO,
                                                  @NonNull @PathVariable Long recipeId) {
        var recipe = recipesService.updateRecipe(recipeDTO, recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete existing Recipe by recipeId.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Recipe deleted"),
            @ApiResponse(code = 404, message = "Recipe Not Found."),
            @ApiResponse(code = 500, message = "Exception from server"),
    })
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipeById(@NonNull @PathVariable Long recipeId) {
        recipesService.deleteRecipeById(recipeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
