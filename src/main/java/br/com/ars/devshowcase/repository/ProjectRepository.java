package br.com.ars.devshowcase.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ars.devshowcase.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByTechnologyContainingIgnoreCase(String technology, Pageable pageable);
}