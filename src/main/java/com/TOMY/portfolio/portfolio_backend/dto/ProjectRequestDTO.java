package com.TOMY.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class ProjectRequestDTO {
    
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String name;
    
    private String description;
    private String imageUrl;
    private String repositoryUrl;
    private String liveUrl;
    private Set<Long> technologyIds;

    // Constructors
    public ProjectRequestDTO() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public Set<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(Set<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }
}
