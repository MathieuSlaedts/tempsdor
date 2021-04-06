package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoleSmallDTO;
import be.tempsdor.tempsdor.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleSmallMapper {

    public RoleSmallDTO toDTO(Role role) {
        return role == null
                ? null
                : RoleSmallDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
