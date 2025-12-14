package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.ExperienceDTO;
import com.TOMY.portfolio.portfolio_backend.model.Experience;
import com.TOMY.portfolio.portfolio_backend.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    public List<ExperienceDTO> getAllExperiences() {
        return experienceRepository.findAllByOrderByStartDateDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExperienceDTO getExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada con id: " + id));
        return convertToDTO(experience);
    }

    public ExperienceDTO createExperience(ExperienceDTO experienceDTO) {
        Experience experience = new Experience();
        experience.setTitle(experienceDTO.getTitle());
        experience.setCompany(experienceDTO.getCompany());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setIsCurrent(experienceDTO.getIsCurrent() != null ? experienceDTO.getIsCurrent() : false);
        experience.setDescription(experienceDTO.getDescription());

        // Si es actual, no debe tener fecha de fin
        if (experience.getIsCurrent() && experienceDTO.getEndDate() != null) {
            experience.setEndDate(null);
        }

        Experience savedExperience = experienceRepository.save(experience);
        return convertToDTO(savedExperience);
    }

    public ExperienceDTO updateExperience(Long id, ExperienceDTO experienceDTO) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada con id: " + id));

        experience.setTitle(experienceDTO.getTitle());
        experience.setCompany(experienceDTO.getCompany());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setIsCurrent(experienceDTO.getIsCurrent() != null ? experienceDTO.getIsCurrent() : false);
        experience.setDescription(experienceDTO.getDescription());

        // Si es actual, no debe tener fecha de fin
        if (experience.getIsCurrent() && experienceDTO.getEndDate() != null) {
            experience.setEndDate(null);
        }

        Experience updatedExperience = experienceRepository.save(experience);
        return convertToDTO(updatedExperience);
    }

    public void deleteExperience(Long id) {
        if (!experienceRepository.existsById(id)) {
            throw new RuntimeException("Experiencia no encontrada con id: " + id);
        }
        experienceRepository.deleteById(id);
    }

    private ExperienceDTO convertToDTO(Experience experience) {
        return new ExperienceDTO(
                experience.getId(),
                experience.getTitle(),
                experience.getCompany(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getIsCurrent(),
                experience.getDescription()
        );
    }
}
