package com.TOMY.portfolio.portfolio_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProfileDTO {
    private Long id;
    
    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;
    
    @NotBlank(message = "El título es obligatorio")
    private String title;
    
    @NotBlank(message = "La ubicación es obligatoria")
    private String location;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    
    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;
    
    private String summary;

    // Constructors
    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String fullName, String title, String location, String email, String phone, String summary) {
        this.id = id;
        this.fullName = fullName;
        this.title = title;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.summary = summary;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
