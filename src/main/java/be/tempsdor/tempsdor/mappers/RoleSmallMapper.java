package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoleSmallDTO;
import be.tempsdor.tempsdor.entities.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RoleSmallMapper {

    public RoleSmallDTO toDTO(Role entity) {
        return entity == null
                ? null
                : RoleSmallDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .uri(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/roles/"+entity.getId())
                .build();
    }
}
