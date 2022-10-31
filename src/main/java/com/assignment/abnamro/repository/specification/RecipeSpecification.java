package com.assignment.abnamro.repository.specification;

import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.entity.RecipeEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeSpecification {

    public static Specification<RecipeEntity> getFilteredRecipes(RecipeFilterDTO recipeFilterDTO) {

        return (Root<RecipeEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (recipeFilterDTO != null) {

                if (!recipeFilterDTO.getTypeOfDiet().isEmpty()) {

                    predicates.add(builder.equal(root.get("typeOfDiet"), recipeFilterDTO.getTypeOfDiet()));
                }
                if (!recipeFilterDTO.getInstructionsSearch().isEmpty()) {

                    predicates.add(builder.like(root.get("instructions"), recipeFilterDTO.getInstructionsSearch()));
                }

                Join<RecipeEntity, IngredientEntity> ingredient = root.join("recipe");

                if (recipeFilterDTO.getIncludedIngredient().size() > 0) {

                    predicates.add(builder.equal(ingredient.get("ingredientName"), recipeFilterDTO.getIncludedIngredient()));
                }

                if (recipeFilterDTO.getExcludedIngredient().size() > 0) {

                    predicates.add(builder.notEqual(ingredient.get("ingredientName"), recipeFilterDTO.getExcludedIngredient()));
                }

                if (recipeFilterDTO.getNumberOfServings() != null) {

                    predicates.add(builder.equal(ingredient.get("numberOfServings"), recipeFilterDTO.getNumberOfServings()));
                }

            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        };
    }


}
