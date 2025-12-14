package com.TOMY.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class ProjectDTO {
    private Long id;
    
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String name;
    
    private String description;
    private String imageUrl;
    private String repositoryUrl;
    private String liveUrl;
    private Set<TechnologyDTO> technologies;

    // Constructors
    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String description, String imageUrl, String repositoryUrl, String liveUrl, Set<TechnologyDTO> technologies) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.repositoryUrl = repositoryUrl;
        this.liveUrl = liveUrl;
        this.technologies = technologies;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<TechnologyDTO> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<TechnologyDTO> technologies) {
        this.technologies = technologies;
    }
}
