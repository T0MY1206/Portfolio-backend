package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.EducationDTO;
import com.TOMY.portfolio.portfolio_backend.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
@Tag(name = "Educations", description = "API para gestionar educación")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping
    @Operation(summary = "Obtener todas las educaciones", description = "Retorna una lista de todas las educaciones ordenadas por fecha")
    public ResponseEntity<List<EducationDTO>> getAllEducations() {
        List<EducationDTO> educations = educationService.getAllEducations();
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener educación por ID", description = "Retorna una educación específica por su ID")
    public ResponseEntity<EducationDTO> getEducationById(@PathVariable Long id) {
        EducationDTO education = educationService.getEducationById(id);
        return ResponseEntity.ok(education);
    }

    @PostMapping
    @Operation(summary = "Crear nueva educación", description = "Crea una nueva entrada de educación")
    public ResponseEntity<EducationDTO> createEducation(@Valid @RequestBody EducationDTO educationDTO) {
        EducationDTO createdEducation = educationService.createEducation(educationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEducation);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar educación", description = "Actualiza una educación existente")
    public ResponseEntity<EducationDTO> updateEducation(@PathVariable Long id, @Valid @RequestBody EducationDTO educationDTO) {
        EducationDTO updatedEducation = educationService.updateEducation(id, educationDTO);
        return ResponseEntity.ok(updatedEducation);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar educación", description = "Elimina una educación por su ID")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }
}
