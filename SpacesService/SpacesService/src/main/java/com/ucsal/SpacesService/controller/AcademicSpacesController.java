package com.ucsal.SpacesService.controller;

import com.ucsal.SpacesService.model.AcademicSpaces;
import com.ucsal.SpacesService.service.AcademicSpacesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
@AllArgsConstructor
public class AcademicSpacesController {

    private final AcademicSpacesService academicSpacesService;

    // GET /api/spaces            → lista todos
    @GetMapping
    public ResponseEntity<List<AcademicSpaces>> getAllSpaces() {
        List<AcademicSpaces> spaces = academicSpacesService.getAllSpaces();
        return ResponseEntity.ok(spaces);
    }

    // GET /api/spaces/{id}       → busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<AcademicSpaces> getSpaceById(@PathVariable Long id) {
        AcademicSpaces space = academicSpacesService.getSpaceById(id);
        return ResponseEntity.ok(space);
    }

    // POST /api/spaces           → cria novo
    @PostMapping
    public ResponseEntity<AcademicSpaces> createSpace(@RequestBody AcademicSpaces newSpace) {
        AcademicSpaces created = academicSpacesService.createSpace(newSpace);
        return ResponseEntity.status(201).body(created);
    }

    // PUT /api/spaces/{id}       → atualiza inteiro
    @PutMapping("/{id}")
    public ResponseEntity<AcademicSpaces> updateSpace(
            @PathVariable Long id,
            @RequestBody AcademicSpaces updatedSpace) {
        AcademicSpaces space = academicSpacesService.updateSpace(id, updatedSpace);
        return ResponseEntity.ok(space);
    }

    // PATCH /api/spaces/{id}/availability?active=true
    // → atualiza apenas “active”
    @PatchMapping("/{id}/availability")
    public ResponseEntity<AcademicSpaces> updateAvailability(
            @PathVariable Long id,
            @RequestParam boolean active) {
        AcademicSpaces space = academicSpacesService.updateAvailability(id, active);
        return ResponseEntity.ok(space);
    }

    // DELETE /api/spaces/{id}    → deleta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        academicSpacesService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }
}
