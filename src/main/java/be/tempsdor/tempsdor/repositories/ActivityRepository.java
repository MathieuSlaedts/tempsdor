package be.tempsdor.tempsdor.repositories;

import be.tempsdor.tempsdor.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findByName(String name);
    Boolean existsByName(String name);
}
