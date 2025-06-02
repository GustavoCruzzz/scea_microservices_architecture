package com.ucsal.SpacesService.service;

import com.ucsal.SpacesService.model.AcademicSpaces;
import com.ucsal.SpacesService.repository.AcademicSpacesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor  // injeta AcademicSpacesRepository via construtor
public class AcademicSpacesService {

    private final AcademicSpacesRepository academicSpacesRepository;

    // Cria um espaço novo
    public AcademicSpaces createSpace(AcademicSpaces academicSpaces) {
        return academicSpacesRepository.save(academicSpaces);
    }

    // Retorna todos os espaços
    public List<AcademicSpaces> getAllSpaces() {
        return academicSpacesRepository.findAll();
    }

    // Busca um espaço por ID
    public AcademicSpaces getSpaceById(Long id) {
        return academicSpacesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Espaço acadêmico não encontrado com id: " + id));
    }

    // Atualiza (substitui) um espaço já existente
    public AcademicSpaces updateSpace(Long id, AcademicSpaces updatedSpace) {
        AcademicSpaces existing = getSpaceById(id);
        existing.setName(updatedSpace.getName());
        existing.setSpaceType(updatedSpace.getSpaceType());
        existing.setHasComputer(updatedSpace.isHasComputer());
        existing.setActive(updatedSpace.isActive());
        existing.setDisableReason(updatedSpace.getDisableReason());
        return academicSpacesRepository.save(existing);
    }

    // Atualiza apenas o campo “active” (por exemplo, ativar/desativar)
    public AcademicSpaces updateAvailability(Long id, boolean active) {
        AcademicSpaces spaces = getSpaceById(id);
        spaces.setActive(active);
        return academicSpacesRepository.save(spaces);
    }

    // Deleta um espaço por ID
    public void deleteSpace(Long id) {
        AcademicSpaces spaces = getSpaceById(id);
        academicSpacesRepository.delete(spaces);
    }
}
