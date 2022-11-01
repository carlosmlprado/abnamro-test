package com.assignment.abnamro.repository.specification;

import com.assignment.abnamro.dto.RecipeFilterDTO;
import com.assignment.abnamro.entity.IngredientEntity;
import com.assignment.abnamro.entity.RecipeEntity;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

@Data
@UtilityClass
public class RecipeSpecification {

    public static Specification<RecipeEntity> getFilteredRecipes(RecipeFilterDTO recipeFilterDTO) {

        return (Root<RecipeEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (recipeFilterDTO != null) {

                if (recipeFilterDTO.getTypeOfDiet() != null) {

                    predicates.add(builder.equal(root.get("typeOfDiet"), recipeFilterDTO.getTypeOfDiet()));
                }
                if (recipeFilterDTO.getInstructionsSearch() != null) {

                    predicates.add(builder.like(root.get("instructions"), "%" + recipeFilterDTO.getInstructionsSearch() + "%"));
                }

                if (recipeFilterDTO.getTypeOfDiet() != null) {

                    predicates.add(builder.like(root.get("typeOfDiet"), recipeFilterDTO.getTypeOfDiet()));
                }

                Join<RecipeEntity, IngredientEntity> ingredient = root.join("ingredients");

                if (recipeFilterDTO.getIncludedIngredient() != null) {

                    predicates.add(ingredient.get("ingredientName").in(recipeFilterDTO.getIncludedIngredient()));
                }

                if (recipeFilterDTO.getServingsNumber() != null) {

                    predicates.add(builder.equal(root.get("servingsNumber"), recipeFilterDTO.getServingsNumber()));
                }

                query.distinct(true);

            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        };
    }


}
