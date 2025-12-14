package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.ProfileDTO;
import com.TOMY.portfolio.portfolio_backend.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "API para gestionar el perfil personal")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    @Operation(summary = "Obtener perfil", description = "Retorna la información del perfil personal")
    public ResponseEntity<ProfileDTO> getProfile() {
        ProfileDTO profile = profileService.getProfile();
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    @Operation(summary = "Crear o actualizar perfil", description = "Crea o actualiza la información del perfil personal")
    public ResponseEntity<ProfileDTO> createOrUpdateProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        ProfileDTO savedProfile = profileService.createOrUpdateProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @PutMapping
    @Operation(summary = "Actualizar perfil", description = "Actualiza la información del perfil personal")
    public ResponseEntity<ProfileDTO> updateProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        ProfileDTO updatedProfile = profileService.createOrUpdateProfile(profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
