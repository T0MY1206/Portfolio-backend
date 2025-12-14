package com.TOMY.portfolio.portfolio_backend.dto;

import java.time.LocalDate;
import java.util.Set;

public class ExperienceResponseDTO {
    private Long id;
    private String title;
    private String company;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrent;
    private Set<TechnologyDTO> technologies;

    // Constructors
    public ExperienceResponseDTO() {
    }

    public ExperienceResponseDTO(Long id, String title, String company, String description, LocalDate startDate, LocalDate endDate, Boolean isCurrent, Set<TechnologyDTO> technologies) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
        this.technologies = technologies;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<TechnologyDTO> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<TechnologyDTO> technologies) {
        this.technologies = technologies;
    }
}
