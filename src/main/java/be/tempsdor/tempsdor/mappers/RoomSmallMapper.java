package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoomSmallDTO;
import be.tempsdor.tempsdor.entities.Room;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class RoomSmallMapper {

    public RoomSmallDTO toDTO(Room entity) {
        return entity == null
                ? null
                : RoomSmallDTO.builder()
                .id(entity.getId())
                .uri(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/rooms/"+entity.getId())
                .build();
    }
}
