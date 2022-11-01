package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.IngredientDTO;
import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.entity.RecipeEntity;
import com.assignment.abnamro.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class IngredientService {

    private IngredientRepository ingredientRepository;

    public RecipeEntity relateIngredientsForRecipe(List<IngredientDTO> ingredientsDTO, RecipeEntity recipeEntity){

        log.info("Create ingredients and relate them to the recipe");
        List<IngredientEntity> ingredientsEntity = new ArrayList<>();

        for(IngredientDTO i: ingredientsDTO){
            var ingredient = new IngredientEntity();
            ingredientsEntity.add(ingredient.toEntity(i, recipeEntity));
        }

        ingredientsEntity = ingredientRepository.saveAll(ingredientsEntity);

        recipeEntity.setIngredients(ingredientsEntity);
        return recipeEntity;
    }
}
