package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.ExperienceRequestDTO;
import com.TOMY.portfolio.portfolio_backend.dto.ExperienceResponseDTO;
import com.TOMY.portfolio.portfolio_backend.dto.TechnologyDTO;
import com.TOMY.portfolio.portfolio_backend.model.Experience;
import com.TOMY.portfolio.portfolio_backend.model.Technology;
import com.TOMY.portfolio.portfolio_backend.repository.ExperienceRepository;
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
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceRepository.findAllByOrderByStartDateDesc()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public ExperienceResponseDTO getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada con id: " + id));
        return convertToResponseDTO(experience);
    }

    public ExperienceResponseDTO createExperience(ExperienceRequestDTO requestDTO) {
        Experience experience = new Experience();
        experience.setTitle(requestDTO.getTitle());
        experience.setCompany(requestDTO.getCompany());
        experience.setDescription(requestDTO.getDescription());
        experience.setStartDate(requestDTO.getStartDate());
        experience.setEndDate(requestDTO.getEndDate());
        experience.setIsCurrent(requestDTO.getIsCurrent() != null ? requestDTO.getIsCurrent() : false);

        // Si es actual, no debe tener fecha de fin
        if (experience.getIsCurrent() && requestDTO.getEndDate() != null) {
            experience.setEndDate(null);
        }

        // Asociar tecnologías
        if (requestDTO.getTechnologyIds() != null && !requestDTO.getTechnologyIds().isEmpty()) {
            Set<Technology> technologies = new HashSet<>();
            for (Long techId : requestDTO.getTechnologyIds()) {
                Technology technology = technologyRepository.findById(techId)
                        .orElseThrow(() -> new RuntimeException("Tecnología no encontrada con id: " + techId));
                technologies.add(technology);
            }
            experience.setTechnologies(technologies);
        }

        Experience savedExperience = experienceRepository.save(experience);
        return convertToResponseDTO(savedExperience);
    }

    public ExperienceResponseDTO updateExperience(Long id, ExperienceRequestDTO requestDTO) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada con id: " + id));

        experience.setTitle(requestDTO.getTitle());
        experience.setCompany(requestDTO.getCompany());
        experience.setDescription(requestDTO.getDescription());
        experience.setStartDate(requestDTO.getStartDate());
        experience.setEndDate(requestDTO.getEndDate());
        experience.setIsCurrent(requestDTO.getIsCurrent() != null ? requestDTO.getIsCurrent() : false);

        // Si es actual, no debe tener fecha de fin
        if (experience.getIsCurrent() && requestDTO.getEndDate() != null) {
            experience.setEndDate(null);
        }

        // Actualizar tecnologías
        if (requestDTO.getTechnologyIds() != null) {
            Set<Technology> technologies = new HashSet<>();
            for (Long techId : requestDTO.getTechnologyIds()) {
                Technology technology = technologyRepository.findById(techId)
                        .orElseThrow(() -> new RuntimeException("Tecnología no encontrada con id: " + techId));
                technologies.add(technology);
            }
            experience.setTechnologies(technologies);
        } else {
            experience.setTechnologies(new HashSet<>());
        }

        Experience updatedExperience = experienceRepository.save(experience);
        return convertToResponseDTO(updatedExperience);
    }

    public void deleteExperience(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new RuntimeException("Experiencia no encontrada con id: " + id);
        }
        experienceRepository.deleteById(id);
    }

    private ExperienceResponseDTO convertToResponseDTO(Experience experience) {
        Set<TechnologyDTO> technologyDTOs = experience.getTechnologies()
                .stream()
                .map(tech -> new TechnologyDTO(tech.getId(), tech.getName(), tech.getType()))
                .collect(Collectors.toSet());

        return new ExperienceResponseDTO(
                experience.getId(),
                experience.getTitle(),
                experience.getCompany(),
                experience.getDescription(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getIsCurrent(),
                technologyDTOs
        );
    }
}
