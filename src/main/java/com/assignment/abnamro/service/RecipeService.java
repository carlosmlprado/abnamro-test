package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.entity.RecipeEntity;
import com.assignment.abnamro.exceptions.RecipesExceptions;
import com.assignment.abnamro.repository.IngredientRepository;
import com.assignment.abnamro.repository.RecipeRepository;
import com.assignment.abnamro.repository.specification.RecipeSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;

    private final IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    public List<RecipeDTO> getAllRecipes() {

        var recipeDTO = new RecipeDTO();
        return recipeRepository.findAll().stream().map(recipeDTO::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RecipeDTO getRecipeById(Long recipeId) {

        var recipeDTO = new RecipeDTO();
        var recipeEntity = checkIfRecipeExists(recipeId);
        return recipeDTO.toDTO(recipeEntity);
    }

    @Transactional
    public RecipeDTO createUpdateRecipe(RecipeDTO recipeDTO, Boolean isUpdate) {
        var recipeEntity = new RecipeEntity();

        log.info("Check if is update to see If the recipeId exists");
        if (isUpdate)
            recipeEntity = checkIfRecipeExists(recipeDTO.getRecipeId());

        recipeEntity = recipeRepository.save(recipeEntity.toEntity(recipeDTO));

        recipeEntity = ingredientService.relateIngredientsForRecipe(recipeDTO.getIngredients(), recipeEntity);

        return recipeDTO.toDTO(recipeEntity);
    }

    @Transactional
    public void deleteRecipe(Long recipeId) {
        var recipeEntity = checkIfRecipeExists(recipeId);
        log.info("Delete ingredients first then recipe");
        ingredientRepository.deleteIngredientByRecipeId(recipeId);
        recipeRepository.delete(recipeEntity);
    }

    @Transactional
    private RecipeEntity checkIfRecipeExists(Long recipeId) {
        return recipeRepository.findById(recipeId).stream().findFirst()
                .orElseThrow(() -> new RecipesExceptions("Recipe not found"));
    }

    @Transactional(readOnly = true)
    public List<RecipeDTO> getFilteredRecipes(RecipeFilterDTO recipeFilterDTO) {

        Specification<RecipeEntity> recipeEntitySpecification = RecipeSpecification.getFilteredRecipes(recipeFilterDTO);

        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);

        Page<RecipeEntity> page = recipeRepository.findAll(recipeEntitySpecification, pageable);
        var recipeDTO = new RecipeDTO();

        var listRecipe = page.getContent().stream().map(recipeDTO::toDTO).collect(Collectors.toList());

        log.info("check if contains excluded ingredient in the recipe");
        if (!page.getContent().isEmpty() && recipeFilterDTO.getExcludedIngredient() != null) {

            checkRecipesWithoutExcludedIngredients(listRecipe, recipeFilterDTO.getExcludedIngredient());
        }

        log.debug("Return list after filtering: {}", listRecipe);
        return listRecipe;
    }

    private void checkRecipesWithoutExcludedIngredients(List<RecipeDTO> recipeDTOS, List<String> excludedIngredients) {

        Set<Long> listWithRecipeIds = recipeDTOS.stream().map(RecipeDTO::getRecipeId).collect(Collectors.toSet());

        for (Long i : listWithRecipeIds) {
            List<IngredientEntity> list = ingredientRepository.getIngredientByNameAndRecipeId(i, excludedIngredients);

            if (list.size() > 0) {
                Predicate<RecipeDTO> predicate = r -> Objects.equals(r.getRecipeId(), i);
                recipeDTOS.removeIf(predicate);
            }

        }

    }

}
