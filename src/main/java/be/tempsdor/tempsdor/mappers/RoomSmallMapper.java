package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoomSmallDTO;
import be.tempsdor.tempsdor.entities.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomSmallMapper {

    public RoomSmallDTO toDTO(Room entity) {
        return entity == null
                ? null
                : RoomSmallDTO.builder()
                .id(entity.getId())
                .build();
    }
}
