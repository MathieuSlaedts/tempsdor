package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoomPertinentDTO;
import be.tempsdor.tempsdor.entities.Room;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomPertinentMapper {

    public RoomPertinentDTO toDTO(Room room) {
        return room == null
                ? null
                : RoomPertinentDTO.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .address(room.getAddress())
                .city(room.getCity())
                .manager(room.getUser().getId())
                .bookings(room.getBookings()
                        .stream()
                        .map(b->b.getId())
                        .collect(Collectors.toSet()))
                .build();
    }

}
