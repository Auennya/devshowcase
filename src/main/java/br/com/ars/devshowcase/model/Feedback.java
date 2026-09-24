package br.com.ars.devshowcase.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Feedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1) @Max(5) @Column(nullable = false)
    private Integer nota;
    private String comentario;
    @ManyToOne @JoinColumn(name = "project_id")
    private Project project;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}