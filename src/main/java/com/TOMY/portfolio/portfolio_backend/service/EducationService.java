package com.TOMY.portfolio.portfolio_backend.service;

import com.TOMY.portfolio.portfolio_backend.dto.EducationDTO;
import com.TOMY.portfolio.portfolio_backend.model.Education;
import com.TOMY.portfolio.portfolio_backend.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public List<EducationDTO> getAllEducations() {
        return educationRepository.findAllByOrderByStartDateDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EducationDTO getEducationById(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Educación no encontrada con id: " + id));
        return convertToDTO(education);
    }

    public EducationDTO createEducation(EducationDTO educationDTO) {
        Education education = new Education();
        education.setDegree(educationDTO.getDegree());
        education.setInstitution(educationDTO.getInstitution());
        education.setStatus(educationDTO.getStatus());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        education.setDescription(educationDTO.getDescription());

        Education savedEducation = educationRepository.save(education);
        return convertToDTO(savedEducation);
    }

    public EducationDTO updateEducation(Long id, EducationDTO educationDTO) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Educación no encontrada con id: " + id));

        education.setDegree(educationDTO.getDegree());
        education.setInstitution(educationDTO.getInstitution());
        education.setStatus(educationDTO.getStatus());
        education.setStartDate(educationDTO.getStartDate());
        education.setEndDate(educationDTO.getEndDate());
        education.setDescription(educationDTO.getDescription());

        Education updatedEducation = educationRepository.save(education);
        return convertToDTO(updatedEducation);
    }

    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new RuntimeException("Educación no encontrada con id: " + id);
        }
        educationRepository.deleteById(id);
    }

    private EducationDTO convertToDTO(Education education) {
        return new EducationDTO(
                education.getId(),
                education.getDegree(),
                education.getInstitution(),
                education.getStatus(),
                education.getStartDate(),
                education.getEndDate(),
                education.getDescription()
        );
    }
}
