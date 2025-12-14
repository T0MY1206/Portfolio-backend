package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.ExperienceRequestDTO;
import com.TOMY.portfolio.portfolio_backend.dto.ExperienceResponseDTO;
import com.TOMY.portfolio.portfolio_backend.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiences")
@Tag(name = "Experiences", description = "API para gestionar experiencias laborales con tecnologías asociadas")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping
    @Operation(summary = "Obtener todas las experiencias", description = "Retorna una lista de todas las experiencias ordenadas por fecha de inicio (más recientes primero)")
    public ResponseEntity<List<ExperienceResponseDTO>> getAllExperiences() {
        List<ExperienceResponseDTO> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener experiencia por ID", description = "Retorna una experiencia específica por su ID")
    public ResponseEntity<ExperienceResponseDTO> getExperienceById(@PathVariable Long id) {
        ExperienceResponseDTO experience = experienceService.getExperienceById(id);
        return ResponseEntity.ok(experience);
    }

    @PostMapping
    @Operation(summary = "Crear nueva experiencia", description = "Crea una nueva experiencia laboral con tecnologías asociadas")
    public ResponseEntity<ExperienceResponseDTO> createExperience(@Valid @RequestBody ExperienceRequestDTO requestDTO) {
        ExperienceResponseDTO createdExperience = experienceService.createExperience(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExperience);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar experiencia", description = "Actualiza una experiencia existente")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(
            @PathVariable Long id,
            @Valid @RequestBody ExperienceRequestDTO requestDTO) {
        ExperienceResponseDTO updatedExperience = experienceService.updateExperience(id, requestDTO);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar experiencia", description = "Elimina una experiencia por su ID")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
