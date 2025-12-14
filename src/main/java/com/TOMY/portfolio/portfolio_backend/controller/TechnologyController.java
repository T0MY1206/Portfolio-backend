package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.TechnologyDTO;
import com.TOMY.portfolio.portfolio_backend.model.Technology;
import com.TOMY.portfolio.portfolio_backend.model.TechnologyType;
import com.TOMY.portfolio.portfolio_backend.repository.TechnologyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/technologies")
@Tag(name = "Technologies", description = "API para gestionar tecnologías (lenguajes, frameworks, herramientas)")
public class TechnologyController {

    @Autowired
    private TechnologyRepository technologyRepository;

    @GetMapping
    @Operation(summary = "Obtener todas las tecnologías", description = "Retorna una lista de todas las tecnologías disponibles")
    public ResponseEntity<List<TechnologyDTO>> getAllTechnologies(
            @RequestParam(required = false) TechnologyType type) {
        List<Technology> technologies;
        if (type != null) {
            technologies = technologyRepository.findByType(type);
        } else {
            technologies = technologyRepository.findAll();
        }
        
        List<TechnologyDTO> dtos = technologies.stream()
                .map(tech -> new TechnologyDTO(tech.getId(), tech.getName(), tech.getType()))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tecnología por ID", description = "Retorna una tecnología específica por su ID")
    public ResponseEntity<TechnologyDTO> getTechnologyById(@PathVariable Long id) {
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tecnología no encontrada con id: " + id));
        
        TechnologyDTO dto = new TechnologyDTO(technology.getId(), technology.getName(), technology.getType());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Crear nueva tecnología", description = "Crea una nueva tecnología (lenguaje, framework, herramienta, etc.)")
    public ResponseEntity<TechnologyDTO> createTechnology(@RequestBody TechnologyDTO requestDTO) {
        if (technologyRepository.existsByName(requestDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Technology technology = new Technology();
        technology.setName(requestDTO.getName());
        technology.setType(requestDTO.getType());

        Technology savedTechnology = technologyRepository.save(technology);
        TechnologyDTO responseDTO = new TechnologyDTO(
                savedTechnology.getId(),
                savedTechnology.getName(),
                savedTechnology.getType()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tecnología", description = "Actualiza una tecnología existente")
    public ResponseEntity<TechnologyDTO> updateTechnology(@PathVariable Long id, @RequestBody TechnologyDTO requestDTO) {
        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tecnología no encontrada con id: " + id));

        // Verificar si el nombre ya existe en otra tecnología
        if (!technology.getName().equals(requestDTO.getName()) && 
            technologyRepository.existsByName(requestDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        technology.setName(requestDTO.getName());
        technology.setType(requestDTO.getType());

        Technology updatedTechnology = technologyRepository.save(technology);
        TechnologyDTO responseDTO = new TechnologyDTO(
                updatedTechnology.getId(),
                updatedTechnology.getName(),
                updatedTechnology.getType()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tecnología", description = "Elimina una tecnología por su ID")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id) {
        if (!technologyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        technologyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
