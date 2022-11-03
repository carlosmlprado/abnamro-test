package com.assignment.abnamro.repository;

import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.entity.RecipeEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    void deleteByRecipe(RecipeEntity recipe);
    
    List<IngredientEntity> findByRecipeAndIngredientNameIn(RecipeEntity recipe, Collection<String> ingredients);

}
