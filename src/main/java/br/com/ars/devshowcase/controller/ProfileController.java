package br.com.ars.devshowcase.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.com.ars.devshowcase.dto.ProfileRequestDTO;
import br.com.ars.devshowcase.model.Profile;
import br.com.ars.devshowcase.repository.ProfileRepository;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final ProfileRepository repository;

    public ProfileController(ProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Profile> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Profile create(@Valid @RequestBody ProfileRequestDTO dto) {
        Profile profile = new Profile();
        profile.setName(dto.getName());
        profile.setBio(dto.getBio());
        profile.setAvatarUrl(dto.getAvatarUrl());
        return repository.save(profile);
    }
}