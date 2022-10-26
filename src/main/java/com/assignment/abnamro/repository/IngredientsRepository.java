package com.assignment.abnamro.repository;

import com.assignment.abnamro.entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository extends JpaRepository<IngredientsEntity, Long> {
}
