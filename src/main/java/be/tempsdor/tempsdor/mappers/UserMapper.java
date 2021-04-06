package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<UserDTO, User> {

    @Autowired
    private final RoleSmallMapper smallRoleMapper;

    @Autowired
    private final RoleRepository roleRepository;

    public UserMapper(RoleSmallMapper smallRoleMapper, RoleRepository roleRepository) {
        this.smallRoleMapper = smallRoleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO toDTO(User entity) {
        return entity == null
                ? null
                : UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .lastname(entity.getLastname())
                .firstname(entity.getFirstname())
                .email(entity.getEmail())
                .roles(Optional.ofNullable(entity.getRoles())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.smallRoleMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        return dto == null
                ? null
                : User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .lastname(dto.getLastname())
                .firstname(dto.getFirstname())
                .email(dto.getEmail())
                .roles(Optional.ofNullable(dto.getRoles())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(c->this.roleRepository.findById(c.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
