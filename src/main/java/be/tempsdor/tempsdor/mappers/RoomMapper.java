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
    public RoomDTO toDTO(Room entity) {
        return entity == null
                ? null
                : RoomDTO.builder()
                .id(entity.getId())
                .capacity(entity.getCapacity())
                .address(entity.getAddress())
                .city(entity.getCity())
                .user(this.userSmallMapper.toDTO(entity.getUser()))
                .activites(entity.getActivities()
                        .stream()
                        .map(this.activitySmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .bookings(entity.getBookings()
                        .stream()
                        .map(this.bookingSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Room toEntity(RoomDTO dto) {
        return dto == null
                ? null
                : Room.builder()
                .id(dto.getId())
                .capacity(dto.getCapacity())
                .address(dto.getAddress())
                .city(dto.getCity())
                .user(this.userRepository.findById(dto.getUser().getId()).orElse(null))
                .activities(dto.getActivites()
                        .stream()
                        .map(r->this.activityRepository.findById(r.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .bookings(dto.getBookings()
                        .stream()
                        .map(r->this.bookingRepository.findById(r.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
