package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.ProjectDTO;
import com.TOMY.portfolio.portfolio_backend.dto.ProjectRequestDTO;
import com.TOMY.portfolio.portfolio_backend.dto.TechnologyDTO;
import com.TOMY.portfolio.portfolio_backend.model.Project;
import com.TOMY.portfolio.portfolio_backend.model.Technology;
import com.TOMY.portfolio.portfolio_backend.repository.ProjectRepository;
import com.TOMY.portfolio.portfolio_backend.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    public List<ProjectDTO> getAllProjects(String lang) {
        return projectRepository.findAllByOrderByIdDesc()
                .stream()
                .map(project -> convertToDTO(project, lang))
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id, String lang) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return convertToDTO(project, lang);
    }

    public ProjectDTO createProject(ProjectRequestDTO requestDTO) {
        if (projectRepository.existsByName(requestDTO.getName())) {
            throw new RuntimeException("Ya existe un proyecto con ese nombre");
        }

        Project project = new Project();
        project.setName(requestDTO.getName());
        project.setDescription(requestDTO.getDescription());
        project.setImageUrl(requestDTO.getImageUrl());
        project.setRepositoryUrl(requestDTO.getRepositoryUrl());
        project.setLiveUrl(requestDTO.getLiveUrl());

        // Asociar tecnologías
        if (requestDTO.getTechnologyIds() != null && !requestDTO.getTechnologyIds().isEmpty()) {
            Set<Technology> technologies = new HashSet<>();
            for (Long techId : requestDTO.getTechnologyIds()) {
                Technology technology = technologyRepository.findById(techId)
                        .orElseThrow(() -> new RuntimeException("Tecnología no encontrada con id: " + techId));
                technologies.add(technology);
            }
            project.setTechnologies(technologies);
        }

        Project savedProject = projectRepository.save(project);
        return convertToDTO(savedProject, "es"); // Idioma por defecto para creación
    }

    public ProjectDTO updateProject(Long id, ProjectRequestDTO requestDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));

        if (!project.getName().equals(requestDTO.getName()) && 
            projectRepository.existsByName(requestDTO.getName())) {
            throw new RuntimeException("Ya existe un proyecto con ese nombre");
        }

        project.setName(requestDTO.getName());
        project.setDescription(requestDTO.getDescription());
        project.setImageUrl(requestDTO.getImageUrl());
        project.setRepositoryUrl(requestDTO.getRepositoryUrl());
        project.setLiveUrl(requestDTO.getLiveUrl());

        // Actualizar tecnologías
        if (requestDTO.getTechnologyIds() != null) {
            Set<Technology> technologies = new HashSet<>();
            for (Long techId : requestDTO.getTechnologyIds()) {
                Technology technology = technologyRepository.findById(techId)
                        .orElseThrow(() -> new RuntimeException("Tecnología no encontrada con id: " + techId));
                technologies.add(technology);
            }
            project.setTechnologies(technologies);
        } else {
            project.setTechnologies(new HashSet<>());
        }

        Project updatedProject = projectRepository.save(project);
        return convertToDTO(updatedProject, "es"); // Idioma por defecto para actualización
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado con id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private ProjectDTO convertToDTO(Project project, String lang) {
        Set<TechnologyDTO> technologyDTOs = project.getTechnologies()
                .stream()
                .map(tech -> new TechnologyDTO(
                        tech.getId(),
                        tech.getName(),
                        tech.getType(),
                        tech.getYearsOfExperience(),
                        tech.getIsCurrentlyUsed()
                ))
                .collect(Collectors.toSet());

        // Obtener nombre y descripción traducidos según el idioma
        String name = getTranslatedName(project, lang);
        String description = getTranslatedDescription(project, lang);

        return new ProjectDTO(
                project.getId(),
                name,
                description,
                project.getImageUrl(),
                project.getRepositoryUrl(),
                project.getLiveUrl(),
                technologyDTOs
        );
    }

    private String getTranslatedName(Project project, String lang) {
        if ("en".equalsIgnoreCase(lang) && project.getNameEn() != null && !project.getNameEn().isEmpty()) {
            return project.getNameEn();
        } else if ("es".equalsIgnoreCase(lang) && project.getNameEs() != null && !project.getNameEs().isEmpty()) {
            return project.getNameEs();
        }
        // Si no hay traducción, devolver el nombre por defecto
        return project.getName();
    }

    private String getTranslatedDescription(Project project, String lang) {
        if ("en".equalsIgnoreCase(lang) && project.getDescriptionEn() != null && !project.getDescriptionEn().isEmpty()) {
            return project.getDescriptionEn();
        } else if ("es".equalsIgnoreCase(lang) && project.getDescriptionEs() != null && !project.getDescriptionEs().isEmpty()) {
            return project.getDescriptionEs();
        }
        // Si no hay traducción, devolver la descripción por defecto
        return project.getDescription();
    }
}
