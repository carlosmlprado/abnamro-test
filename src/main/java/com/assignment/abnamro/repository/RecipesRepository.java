package com.assignment.abnamro.repository;

import com.assignment.abnamro.entity.RecipesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<RecipesEntity, Long> {

}
