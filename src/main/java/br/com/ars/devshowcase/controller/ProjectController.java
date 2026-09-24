package br.com.ars.devshowcase.controller;

import br.com.ars.devshowcase.model.Feedback;
import br.com.ars.devshowcase.model.Project;
import br.com.ars.devshowcase.repository.FeedbackRepository;
import br.com.ars.devshowcase.repository.ProfileRepository;
import br.com.ars.devshowcase.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public Page<Project> getAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        if (project.getProfile() != null && project.getProfile().getId() != null) {
            profileRepository.findById(project.getProfile().getId()).ifPresent(project::setProfile);
        }
        Project saved = projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        projectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<Project> addFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        return projectRepository.findById(id).map(project -> {
            feedback.setProject(project);
            feedbackRepository.save(feedback);
            Double avg = feedbackRepository.findByProjectId(id).stream()
                    .mapToInt(Feedback::getRating).average().orElse(0.0);
            project.setAverageRating(avg);
            return ResponseEntity.ok(projectRepository.save(project));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<Project> upvote(@PathVariable Long id) {
        return projectRepository.findById(id).map(p -> {
            p.setUpvotes(p.getUpvotes() == null ? 1 : p.getUpvotes() + 1);
            return ResponseEntity.ok(projectRepository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }
}