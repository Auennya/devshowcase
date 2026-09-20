package br.com.ars.devshowcase.dto;

import jakarta.validation.constraints.NotBlank;

public class TechnologyRequestDTO {
    @NotBlank(message = "Nome da tecnologia é obrigatório")
    private String name;

    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}