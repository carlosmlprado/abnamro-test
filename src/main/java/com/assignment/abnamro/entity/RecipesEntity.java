package com.assignment.abnamro.entity;

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
public class RecipesEntity {

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

}
