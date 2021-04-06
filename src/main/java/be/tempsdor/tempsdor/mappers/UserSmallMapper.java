package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserSmallDTO;
import be.tempsdor.tempsdor.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserSmallMapper {

    UserSmallDTO toDTO(User entity) {
        return UserSmallDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }
}
