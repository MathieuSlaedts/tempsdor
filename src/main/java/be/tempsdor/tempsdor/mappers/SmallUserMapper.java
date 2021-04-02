package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.SmallUserDTO;
import be.tempsdor.tempsdor.entities.User;

public class SmallUserMapper {

    SmallUserDTO toDTO(User entity) {
        return SmallUserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }
}
