package br.com.ars.devshowcase.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.ars.devshowcase.model.Feedback;
import br.com.ars.devshowcase.model.Project;
import br.com.ars.devshowcase.repository.FeedbackRepository;
import br.com.ars.devshowcase.repository.ProjectRepository;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private FeedbackRepository feedbackRepository;

    @GetMapping
    public Page<Project> getAll(
        @RequestParam(required = false) String technology,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (technology != null && !technology.isEmpty()) {
            return projectRepository.findByTechnologyContainingIgnoreCase(technology, pageable);
        }
        return projectRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.status(201).body(projectRepository.save(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<Project> addFeedback(@PathVariable Long id, @RequestBody Feedback req) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        Feedback f = new Feedback();
        f.setNota(req.getNota());
        f.setComentario(req.getComentario());
        f.setProject(project);
        feedbackRepository.save(f);
        List<Feedback> todos = feedbackRepository.findByProjectId(id);
        double media = todos.stream().mapToInt(Feedback::getNota).average().orElse(0.0);
        project.setNotaMedia(media);
        project.setTotalFeedbacks(todos.size());
        return ResponseEntity.status(201).body(projectRepository.save(project));
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<Project> upvote(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        if (project.getLikes() == null) project.setLikes(0);
        project.setLikes(project.getLikes() + 1);
        return ResponseEntity.ok(projectRepository.save(project));
    }
}