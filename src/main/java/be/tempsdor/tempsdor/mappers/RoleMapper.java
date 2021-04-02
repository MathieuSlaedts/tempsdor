package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoleDTO;
import be.tempsdor.tempsdor.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<RoleDTO, Role> {

    @Override
    public RoleDTO toDTO(Role entity) {
        return entity == null
                ? null
                : RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        return dto == null
                ? null
                : Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
