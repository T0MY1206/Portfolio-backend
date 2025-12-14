package com.TOMY.portfolio.portfolio_backend.controller;

import com.TOMY.portfolio.portfolio_backend.dto.ProjectDTO;
import com.TOMY.portfolio.portfolio_backend.dto.ProjectRequestDTO;
import com.TOMY.portfolio.portfolio_backend.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "API para gestionar proyectos con tecnologías asociadas")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(summary = "Obtener todos los proyectos", description = "Retorna una lista de todos los proyectos con sus tecnologías")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proyecto por ID", description = "Retorna un proyecto específico por su ID")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        ProjectDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo proyecto", description = "Crea un nuevo proyecto con tecnologías asociadas")
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectRequestDTO requestDTO) {
        ProjectDTO createdProject = projectService.createProject(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proyecto", description = "Actualiza un proyecto existente")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectRequestDTO requestDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, requestDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proyecto", description = "Elimina un proyecto por su ID")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
