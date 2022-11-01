package com.assignment.abnamro.entity;

import com.assignment.abnamro.dto.IngredientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient")
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "ingredient_measurement")
    private String ingredientMeasurement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    public IngredientEntity toEntity(IngredientDTO ingredientDTO, RecipeEntity recipeEntity){

        return IngredientEntity.builder().
                ingredientId(ingredientDTO.getId()).
                ingredientName(ingredientDTO.getIngredientName()).
                quantity(ingredientDTO.getQuantity()).
                ingredientMeasurement(null != ingredientDTO.getIngredientMeasurements() ? ingredientDTO.getIngredientMeasurements().toUpperCase() : null).
                recipe(recipeEntity).
                build();
    }

}
