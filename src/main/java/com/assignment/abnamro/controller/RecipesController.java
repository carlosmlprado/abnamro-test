package com.assignment.abnamro.controller;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/recipes")
@RestController
@AllArgsConstructor
public class RecipesController {

    private RecipeService recipesService;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(){
        return ResponseEntity.ok(recipesService.getAll());
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipeById(@NonNull @PathVariable Long recipeId){
        return ResponseEntity.ok(recipesService.getRecipeById(recipeId));
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO){
        var recipe = recipesService.createUpdateRecipe(recipeDTO, false);
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDTO){
        var recipe = recipesService.createUpdateRecipe(recipeDTO, true);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipeById(@NonNull @PathVariable Long recipeId){
        recipesService.deleteRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
