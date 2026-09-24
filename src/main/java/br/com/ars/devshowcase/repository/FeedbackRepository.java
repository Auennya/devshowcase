package br.com.ars.devshowcase.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ars.devshowcase.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByProjectId(Long projectId);
}