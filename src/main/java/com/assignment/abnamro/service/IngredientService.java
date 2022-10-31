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

    public void relateIngredientsForRecipe(List<IngredientDTO> ingredientsDTO, RecipeEntity recipeEntity){

        log.info("Create ingredients and relate to the recipe");
        var ingredientsEntity = new ArrayList<IngredientEntity>();

        for(IngredientDTO i: ingredientsDTO){
            var ingredient = new IngredientEntity();
            ingredient.setRecipe(recipeEntity);
            ingredientsEntity.add(ingredient.toEntity(i));
        }

        ingredientRepository.saveAll(ingredientsEntity);
    }
}
