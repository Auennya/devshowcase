package br.com.ars.devshowcase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}