package com.example.eclipselink.repository;

import com.example.eclipselink.model.Calculated;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatedRepository extends JpaRepository<Calculated,Integer> {
}
