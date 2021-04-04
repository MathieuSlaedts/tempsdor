package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, User> {
    private final SmallRoleMapper smallRoleMapper;
    private final RoleRepository roleRepository;

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
                .role(this.smallRoleMapper.toDTO(entity.getRole()))
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
                .role(this.roleRepository.findById(dto.getRole().getId()).orElse(null))
                .build();
    }
}
