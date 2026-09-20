package br.com.ars.devshowcase.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.com.ars.devshowcase.dto.TechnologyRequestDTO;
import br.com.ars.devshowcase.dto.TechnologyResponseDTO;
import br.com.ars.devshowcase.model.Technology;
import br.com.ars.devshowcase.repository.TechnologyRepository;

@RestController
@RequestMapping("/api/technologies")
@CrossOrigin(origins = "*")
public class TechnologyController {

    private final TechnologyRepository repository;

    public TechnologyController(TechnologyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Technology> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public TechnologyResponseDTO create(@Valid @RequestBody TechnologyRequestDTO dto) {
        Technology tech = new Technology();
        tech.setName(dto.getName());
        tech.setDescription(dto.getDescription());

        Technology saved = repository.save(tech);

        TechnologyResponseDTO response = new TechnologyResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());
        return response;
    }
}