package be.tempsdor.tempsdor.repositories;

import be.tempsdor.tempsdor.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Boolean existsByName(String name);
}
