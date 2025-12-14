package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.ExperienceDTO;
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
@Tag(name = "Experiences", description = "API para gestionar experiencia laboral")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping
    @Operation(summary = "Obtener todas las experiencias", description = "Retorna una lista de todas las experiencias laborales ordenadas por fecha")
    public ResponseEntity<List<ExperienceDTO>> getAllExperiences() {
        List<ExperienceDTO> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener experiencia por ID", description = "Retorna una experiencia específica por su ID")
    public ResponseEntity<ExperienceDTO> getExperienceById(@PathVariable Long id) {
        ExperienceDTO experience = experienceService.getExperienceById(id);
        return ResponseEntity.ok(experience);
    }

    @PostMapping
    @Operation(summary = "Crear nueva experiencia", description = "Crea una nueva experiencia laboral")
    public ResponseEntity<ExperienceDTO> createExperience(@Valid @RequestBody ExperienceDTO experienceDTO) {
        ExperienceDTO createdExperience = experienceService.createExperience(experienceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExperience);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar experiencia", description = "Actualiza una experiencia existente")
    public ResponseEntity<ExperienceDTO> updateExperience(@PathVariable Long id, @Valid @RequestBody ExperienceDTO experienceDTO) {
        ExperienceDTO updatedExperience = experienceService.updateExperience(id, experienceDTO);
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar experiencia", description = "Elimina una experiencia por su ID")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.noContent().build();
    }
}
