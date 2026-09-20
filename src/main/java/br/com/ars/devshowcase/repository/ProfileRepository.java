package br.com.ars.devshowcase.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ars.devshowcase.model.Profile;
public interface ProfileRepository extends JpaRepository<Profile, Long> {}