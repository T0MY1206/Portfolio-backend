package com.TOMY.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class EducationDTO {
    private Long id;
    
    @NotBlank(message = "El título/degree es obligatorio")
    private String degree;
    
    @NotBlank(message = "La institución es obligatoria")
    private String institution;
    
    @NotBlank(message = "El estado es obligatorio")
    private String status;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    // Constructors
    public EducationDTO() {
    }

    public EducationDTO(Long id, String degree, String institution, String status, LocalDate startDate, LocalDate endDate, String description) {
        this.id = id;
        this.degree = degree;
        this.institution = institution;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
