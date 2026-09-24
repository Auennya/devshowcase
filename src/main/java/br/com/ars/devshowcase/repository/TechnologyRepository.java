package br.com.ars.devshowcase.repository;

import br.com.ars.devshowcase.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
    Optional<Technology> findByName(String name);
}