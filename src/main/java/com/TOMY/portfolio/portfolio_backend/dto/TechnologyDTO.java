package com.TOMY.portfolio.portfolio_backend.dto;

import com.TOMY.portfolio.portfolio_backend.model.TechnologyType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TechnologyDTO {
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    
    @NotNull(message = "El tipo es obligatorio")
    private TechnologyType type;
    
    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "Los años de experiencia deben ser mayor o igual a 0")
    private Integer yearsOfExperience;
    
    private Boolean isCurrentlyUsed;

    // Constructors
    public TechnologyDTO() {
    }

    public TechnologyDTO(Long id, String name, TechnologyType type, Integer yearsOfExperience, Boolean isCurrentlyUsed) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.yearsOfExperience = yearsOfExperience;
        this.isCurrentlyUsed = isCurrentlyUsed;
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

    public TechnologyType getType() {
        return type;
    }

    public void setType(TechnologyType type) {
        this.type = type;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Boolean getIsCurrentlyUsed() {
        return isCurrentlyUsed;
    }

    public void setIsCurrentlyUsed(Boolean isCurrentlyUsed) {
        this.isCurrentlyUsed = isCurrentlyUsed;
    }
}
