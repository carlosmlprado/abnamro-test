package com.assignment.abnamro.service;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.exceptions.RecipesExceptions;
import com.assignment.abnamro.repository.RecipesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipesService {

    private final RecipesRepository recipesRepository;

    public List<RecipeDTO> getAll() {

        var recipeDTO = new RecipeDTO();
        return recipesRepository.findAll().stream().map(recipeDTO::toDTO).collect(Collectors.toList());
    }

    public RecipeDTO getRecipeById(Long id) {

        var recipeDTO = new RecipeDTO();
        var recipeEntity = recipesRepository.findById(id).stream().findFirst()
                .orElseThrow(() -> new RecipesExceptions("Recipe not found"));

        return recipeDTO.toDTO(recipeEntity);

    }

}
