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

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAllByOrderByIdDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + id));
        return convertToDTO(project);
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
        return convertToDTO(savedProject);
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
        return convertToDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado con id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private ProjectDTO convertToDTO(Project project) {
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

        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getImageUrl(),
                project.getRepositoryUrl(),
                project.getLiveUrl(),
                technologyDTOs
        );
    }
}
