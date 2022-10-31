package com.assignment.abnamro.repository;

import com.assignment.abnamro.dto.RecipeDTO;
import com.assignment.abnamro.dto.model.Recipe;
import com.assignment.abnamro.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity> {

    @Query(value = "select r.id, r.recipe_name, r.servings_number, r.instructions" +
            "i.id, i.ingredient_name, i.quantity, i.ingredient_measurement from recipes r " +
            "inner join ingredients i on r.id = i.recipe_id", nativeQuery = true)
    public List<Recipe> getRecipesAndIngredients();
}
