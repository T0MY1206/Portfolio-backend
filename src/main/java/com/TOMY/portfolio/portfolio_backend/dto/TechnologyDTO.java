package com.TOMY.portfolio.portfolio_backend.dto;

import com.TOMY.portfolio.portfolio_backend.model.TechnologyType;

public class TechnologyDTO {
    private Long id;
    private String name;
    private TechnologyType type;

    // Constructors
    public TechnologyDTO() {
    }

    public TechnologyDTO(Long id, String name, TechnologyType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
}
