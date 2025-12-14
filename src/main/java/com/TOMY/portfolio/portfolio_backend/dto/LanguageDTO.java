package com.TOMY.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LanguageDTO {
    private Long id;
    
    @NotBlank(message = "El nombre del idioma es obligatorio")
    private String name;
    
    @NotBlank(message = "El nivel es obligatorio")
    private String level;

    // Constructors
    public LanguageDTO() {
    }

    public LanguageDTO(Long id, String name, String level) {
        this.id = id;
        this.name = name;
        this.level = level;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
