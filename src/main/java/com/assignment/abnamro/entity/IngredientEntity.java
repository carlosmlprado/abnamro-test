package com.assignment.abnamro.entity;

import com.assignment.abnamro.dto.IngredientsDTO;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "ingredient_measurement")
    private String ingredientMeasurement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private RecipeEntity recipe;

    public IngredientEntity toEntity(IngredientsDTO ingredientsDTO){

        return this.builder().
                ingredientName(ingredientsDTO.getIngredientName()).
                quantity(ingredientsDTO.getQuantity()).
                ingredientMeasurement(ingredientsDTO.getIngredientMeasurements().toString().toUpperCase()).
                build();
    }

}
