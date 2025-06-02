package com.ucsal.SpacesService.repository;

import com.ucsal.SpacesService.model.AcademicSpaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicSpacesRepository extends JpaRepository<AcademicSpaces, Long> {
    // Se precisar de métodos personalizados, declare aqui, por exemplo:
    // List<AcademicSpaces> findByActiveTrue();
}
