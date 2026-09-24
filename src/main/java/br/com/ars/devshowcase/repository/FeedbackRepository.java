package br.com.ars.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import br.com.ars.devshowcase.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByProjectId(Long projectId);
}