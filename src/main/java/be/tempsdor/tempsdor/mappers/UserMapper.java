package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<UserDTO, User> {

    @Autowired
    private SmallRoleMapper smallRoleMapper;

    @Autowired
    private RoleRepository roleRepository;

    public UserMapper(RoleMapper roleMapper, SmallRoleMapper smallRoleMapper, RoleRepository roleRepository) {
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
                .roles(entity.getRoles()
                        .stream()
                        .map(this.smallRoleMapper::toDTO)
                        .collect(Collectors.toList()))
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
                .roles(dto.getRoles()
                        .stream()
                        .map(c->this.roleRepository.findById(c.getId()).orElse(null))
                        .collect(Collectors.toList()))
                .build();
    }
}
