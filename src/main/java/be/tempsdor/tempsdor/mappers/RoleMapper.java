package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoleDTO;
import be.tempsdor.tempsdor.entities.Role;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoleMapper implements Mapper<RoleDTO, Role> {

    @Autowired
    private UserSmallMapper smallUserMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RoleDTO toDTO(Role entity) {
        return entity == null
                ? null
                : RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .users(Optional.ofNullable(entity.getUsers())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.smallUserMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        return dto == null
                ? null
                : Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .users(Optional.ofNullable(dto.getUsers())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(c->this.userRepository.findById(c.getId()).orElseThrow(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
