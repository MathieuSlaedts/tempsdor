package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserSmallDTO;
import be.tempsdor.tempsdor.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UserSmallMapper {

    UserSmallDTO toDTO(User entity) {
        return UserSmallDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .uri(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/users/"+entity.getId())
                .build();
    }
}