package br.com.rest.api.spring.mscorinthans.repositories;

import br.com.rest.api.spring.mscorinthans.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
