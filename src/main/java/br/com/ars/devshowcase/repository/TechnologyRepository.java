package br.com.ars.devshowcase.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ars.devshowcase.model.Technology;
public interface TechnologyRepository extends JpaRepository<Technology, Long> {}