package br.com.ars.devshowcase.service;

import br.com.ars.devshowcase.model.Feedback;
import br.com.ars.devshowcase.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback salvar(Feedback feedback) {
        return repository.save(feedback);
    }

    public List<Feedback> listar() {
        return repository.findAll();
    }
}