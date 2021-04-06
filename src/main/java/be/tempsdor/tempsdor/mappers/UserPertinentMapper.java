package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.entities.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserPertinentMapper {

    private final RoleSmallMapper smallRoleMapper;

    public UserPertinentMapper(RoleSmallMapper smallRoleMapper) {
        this.smallRoleMapper = smallRoleMapper;
    }

    public UserPertinentDTO toDTO(User entity) {
        return entity == null
                ? null
                : UserPertinentDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
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
}
