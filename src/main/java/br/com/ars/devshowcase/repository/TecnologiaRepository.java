package br.com.ars.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ars.devshowcase.model.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
}