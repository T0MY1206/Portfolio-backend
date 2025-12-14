package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.LanguageDTO;
import com.TOMY.portfolio.portfolio_backend.service.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@Tag(name = "Languages", description = "API para gestionar idiomas")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    @Operation(summary = "Obtener todos los idiomas", description = "Retorna una lista de todos los idiomas")
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        List<LanguageDTO> languages = languageService.getAllLanguages();
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener idioma por ID", description = "Retorna un idioma específico por su ID")
    public ResponseEntity<LanguageDTO> getLanguageById(@PathVariable Long id) {
        LanguageDTO language = languageService.getLanguageById(id);
        return ResponseEntity.ok(language);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo idioma", description = "Crea un nuevo idioma")
    public ResponseEntity<LanguageDTO> createLanguage(@Valid @RequestBody LanguageDTO languageDTO) {
        LanguageDTO createdLanguage = languageService.createLanguage(languageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLanguage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar idioma", description = "Actualiza un idioma existente")
    public ResponseEntity<LanguageDTO> updateLanguage(@PathVariable Long id, @Valid @RequestBody LanguageDTO languageDTO) {
        LanguageDTO updatedLanguage = languageService.updateLanguage(id, languageDTO);
        return ResponseEntity.ok(updatedLanguage);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar idioma", description = "Elimina un idioma por su ID")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}
