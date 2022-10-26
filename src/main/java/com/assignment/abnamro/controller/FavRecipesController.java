package com.assignment.abnamro.controller;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.service.RecipesService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/recipes")
@RestController
@Slf4j
public class FavRecipesController {

    private RecipesService recipesService;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(){
        return ResponseEntity.ok(recipesService.getAll());
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipeById(@NonNull @PathVariable Long recipeId){
        return ResponseEntity.ok(recipesService.getRecipeById(recipeId));
    }
}
