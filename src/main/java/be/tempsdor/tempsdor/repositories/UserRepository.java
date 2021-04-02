package be.tempsdor.tempsdor.repositories;

import be.tempsdor.tempsdor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
