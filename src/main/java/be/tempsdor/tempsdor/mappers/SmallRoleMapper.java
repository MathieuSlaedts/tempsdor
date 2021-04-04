package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.SmallRoleDTO;
import be.tempsdor.tempsdor.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class SmallRoleMapper {

    public SmallRoleDTO toDTO(Role role) {
        return role == null
                ? null
                : SmallRoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
