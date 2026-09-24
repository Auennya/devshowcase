package br.com.ars.devshowcase.controller;

import br.com.ars.devshowcase.model.Technology;
import br.com.ars.devshowcase.repository.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyRepository technologyRepository;

    @GetMapping
    public Page<Technology> getAll(Pageable pageable) {
        return technologyRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Technology> create(@RequestBody Technology technology) {
        Technology saved = technologyRepository.save(technology);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!technologyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        technologyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}