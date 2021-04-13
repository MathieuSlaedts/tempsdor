package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.RoomDTO;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.repositories.ActivityRepository;
import be.tempsdor.tempsdor.repositories.BookingRepository;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomMapper implements Mapper<RoomDTO, Room> {

    private final UserSmallMapper userSmallMapper;
    private final UserRepository userRepository;

    private final ActivitySmallMapper activitySmallMapper;
    private final ActivityRepository activityRepository;

    private final BookingSmallMapper bookingSmallMapper;
    private final BookingRepository bookingRepository;

    public RoomMapper(UserSmallMapper userSmallMapper, UserRepository userRepository, ActivitySmallMapper activitySmallMapper, ActivityRepository activityRepository, BookingSmallMapper bookingSmallMapper, BookingRepository bookingRepository) {
        this.userSmallMapper = userSmallMapper;
        this.userRepository = userRepository;
        this.activitySmallMapper = activitySmallMapper;
        this.activityRepository = activityRepository;
        this.bookingSmallMapper = bookingSmallMapper;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public RoomDTO toDTO(Room room) {
        return room == null
                ? null
                : RoomDTO.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .address(room.getAddress())
                .city(room.getCity())
                .user(this.userSmallMapper.toDTO(room.getUser()))
                .activites(room.getActivities()
                        .stream()
                        .map(this.activitySmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .bookings(room.getBookings()
                        .stream()
                        .map(this.bookingSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Room toEntity(RoomDTO roomDTO) {
        return roomDTO == null
                ? null
                : Room.builder()
                .id(roomDTO.getId())
                .capacity(roomDTO.getCapacity())
                .address(roomDTO.getAddress())
                .city(roomDTO.getCity())
                .user(this.userRepository.findById(roomDTO.getUser().getId()).orElse(null))
                .activities(roomDTO.getActivites()
                        .stream()
                        .map(r->this.activityRepository.findById(r.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .bookings(roomDTO.getBookings()
                        .stream()
                        .map(r->this.bookingRepository.findById(r.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
