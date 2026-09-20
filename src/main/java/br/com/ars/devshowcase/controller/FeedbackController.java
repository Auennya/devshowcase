package br.com.ars.devshowcase.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ars.devshowcase.model.Feedback;
import br.com.ars.devshowcase.service.FeedbackService;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {
    private final FeedbackService service;
    public FeedbackController(FeedbackService service) {
        this.service = service;
    }
    @PostMapping
    public Feedback criar(@RequestBody Feedback feedback) {
        return service.salvar(feedback);
    }
    @GetMapping
    public List<Feedback> listar() {
        return service.listar();
    }
}