package com.assignment.abnamro.entity;

import com.assignment.abnamro.enums.IngredientMeasurements;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredients")
public class IngredientsEntity {

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


}
