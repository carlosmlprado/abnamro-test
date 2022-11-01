package com.assignment.abnamro.helpers;

import com.assignment.abnamro.dto.IngredientDTO;
import com.assignment.abnamro.entity.IngredientEntity;

import java.util.ArrayList;
import java.util.List;

public class IngredientHelper {

    public List<IngredientEntity> createIngredientMockList(){

        List<IngredientEntity> list = new ArrayList<>();

        IngredientEntity ingredient1 = new IngredientEntity();
        ingredient1.setIngredientId(10L);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setIngredientId(11L);

        list.add(ingredient1);
        list.add(ingredient2);

        return list;
    }

    public List<IngredientDTO> createIngredientMockListDTO(){

        List<IngredientDTO> list = new ArrayList<>();

        IngredientDTO ingredient1 = new IngredientDTO();
        ingredient1.setId(10L);

        IngredientDTO ingredient2 = new IngredientDTO();
        ingredient2.setId(11L);

        list.add(ingredient1);
        list.add(ingredient2);

        return list;
    }
}
