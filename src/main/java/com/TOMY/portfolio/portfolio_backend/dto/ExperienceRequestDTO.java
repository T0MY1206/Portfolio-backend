package com.TOMY.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

public class ExperienceRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotBlank(message = "La empresa es obligatoria")
    private String company;

    private String description;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrent = false;

    private Set<Long> technologyIds;

    // Constructors
    public ExperienceRequestDTO() {
    }

    public ExperienceRequestDTO(String title, String company, String description, LocalDate startDate, LocalDate endDate, Boolean isCurrent, Set<Long> technologyIds) {
        this.title = title;
        this.company = company;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
        this.technologyIds = technologyIds;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Set<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(Set<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }
}
