package com.assignment.abnamro.repository;

import com.assignment.abnamro.entity.IngredientEntity;
import feign.Param;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    @Modifying
    @Query(value = "delete from ingredient where recipe_id =:recipeId", nativeQuery = true)
    void deleteIngredientByRecipeId(@Param("recipeId") Long recipeId);
}
