package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoomPertinentDTO;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.repositories.ActivityRepository;
import be.tempsdor.tempsdor.repositories.BookingRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoomPertinentMapper {
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final BookingRepository bookingRepository;

    public RoomPertinentMapper(UserRepository userRepository, ActivityRepository activityRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.bookingRepository = bookingRepository;
    }

    public RoomPertinentDTO toDTO(Room entity) {
        return entity == null
                ? null
                : RoomPertinentDTO.builder()
                .id(entity.getId())
                .capacity(entity.getCapacity())
                .address(entity.getAddress())
                .city(entity.getCity())
                .manager(entity.getUser().getId())
                .bookings(Optional.ofNullable(entity.getBookings())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(b -> b == null ? null : b.getId())
                        .collect(Collectors.toSet()))
                .activities(Optional.ofNullable(entity.getActivities())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(a -> a == null ? null : a.getId())
                        .collect(Collectors.toSet()))
                .build();
    }
    public Room toEntity(RoomPertinentDTO dto) {
        return dto == null
                ? null
                : Room.builder()
                .id(dto.getId())
                .capacity(dto.getCapacity())
                .address(dto.getAddress())
                .city(dto.getCity())
                .user(this.userRepository.findById(dto.getManager()).orElse(null))
                .bookings(Optional.ofNullable(dto.getBookings())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(b -> b == null ? null : this.bookingRepository.findById(b).orElse(null))
                        .collect(Collectors.toSet()))
                .activities(Optional.ofNullable(dto.getActivities())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(a -> a == null ? null : this.activityRepository.findById(a).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }

}
