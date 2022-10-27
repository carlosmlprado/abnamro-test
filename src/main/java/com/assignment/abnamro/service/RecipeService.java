package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.entity.RecipeEntity;
import com.assignment.abnamro.exceptions.RecipesExceptions;
import com.assignment.abnamro.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    public List<RecipeDTO> getAll() {

        var recipeDTO = new RecipeDTO();
        return recipeRepository.findAll().stream().map(recipeDTO::toDTO).collect(Collectors.toList());
    }

    public RecipeDTO getRecipeById(Long recipeId) {

        var recipeDTO = new RecipeDTO();
        var recipeEntity = checkIfRecipeExists(recipeId);
        return recipeDTO.toDTO(recipeEntity);
    }

    public RecipeDTO createUpdateRecipe(RecipeDTO recipeDTO, Boolean isUpdate) {
        var recipeEntity = new RecipeEntity();

        log.info("Check if is update to see If the recipeId exists");
        if (isUpdate)
            recipeEntity = checkIfRecipeExists(recipeDTO.getId());

        recipeEntity = recipeRepository.save(recipeEntity.toEntity(recipeDTO));

        ingredientService.relateIngredientsForRecipe(recipeDTO.getIngredients(), recipeEntity);

        return recipeDTO.toDTO(recipeEntity);
    }

    public void deleteRecipe(Long recipeId) {
        var recipeEntity = checkIfRecipeExists(recipeId);
        recipeRepository.delete(recipeEntity);
    }

    private RecipeEntity checkIfRecipeExists(Long recipeId) {
        return recipeRepository.findById(recipeId).stream().findFirst()
                .orElseThrow(() -> new RecipesExceptions("Recipe not found"));
    }

}
