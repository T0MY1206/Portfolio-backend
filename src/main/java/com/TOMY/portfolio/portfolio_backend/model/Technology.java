package com.TOMY.portfolio.portfolio_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechnologyType type;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Boolean isCurrentlyUsed = false;

    // Constructors
    public Technology() {
    }

    public Technology(String name, TechnologyType type, Integer yearsOfExperience, Boolean isCurrentlyUsed) {
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
