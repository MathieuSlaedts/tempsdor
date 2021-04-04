package be.tempsdor.tempsdor;

import be.tempsdor.tempsdor.entities.Role;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Role userRole = roleRepository.findByName("ROLE_USER");

        createUserIfNotFound("john_doe", new ArrayList<Role>(List.of(adminRole,userRole)));
        createUserIfNotFound("jane_doe", new ArrayList<Role>(List.of(adminRole)));
        createUserIfNotFound("jonnie_doe", new ArrayList<Role>(List.of(userRole)));
        createUserIfNotFound("janie_doe", null);
        alreadySetup = true;


    }

    @Transactional
    private User createUserIfNotFound(String username, List<Role> roles) {

        User user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            String[] usernameParts = username.split("_");
            user = new User();
            user.setId(0);
            user.setUsername(username);
            user.setFirstname(usernameParts[0].substring(0, 1).toUpperCase() + usernameParts[0].substring(1));
            user.setLastname(usernameParts[1].substring(0, 1).toUpperCase() + usernameParts[1].substring(1));
            user.setPassword(passwordEncoder.encode("passwordByDefault"));
            user.setEmail(usernameParts[0] + "-" + usernameParts[1] + "@email.com");
            user.setRoles(roles);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            this.userRepository.save(user);
        }
        return user;
    }

    @Transactional
    private Role createRoleIfNotFound(String name) {

        Role role = this.roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setId(0);
            role.setName(name);
            this.roleRepository.save(role);
        }
        return role;
    }
}
