package com.assignment.abnamro.entity;

import com.assignment.abnamro.dto.RecipeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "servings_number")
    private Integer servingsNumber;

    @Column(name = "instructions", length = 5000)
    private String instructions;


    public RecipeEntity toEntity(RecipeDTO recipeDTO) {

        return this.builder().
                recipeName(recipeDTO.getRecipeName()).
                servingsNumber(recipeDTO.getServingsNumber()).
                instructions(recipeDTO.getInstructions()).build();
    }

}
