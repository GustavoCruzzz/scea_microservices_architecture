package com.ucsal.SpacesService.repository;

import com.ucsal.SpacesService.model.AcademicSpaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicSpacesRepository extends JpaRepository<AcademicSpaces, Long> {
    List<AcademicSpaces> findByActiveTrue();
}