package com.assignment.abnamro.repository;

import com.assignment.abnamro.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
}
