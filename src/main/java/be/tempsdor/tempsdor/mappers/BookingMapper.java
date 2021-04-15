package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper implements Mapper<BookingDTO, Booking>{
    private final UserSmallMapper userSmallMapper;
    private final UserRepository userRepository;

    private final RoomSmallMapper roomSmallMapper;
    private final RoomRepository roomRepository;

    public BookingMapper(UserSmallMapper userSmallMapper, UserRepository userRepository, RoomSmallMapper roomSmallMapper, RoomRepository roomRepository) {
        this.userSmallMapper = userSmallMapper;
        this.userRepository = userRepository;
        this.roomSmallMapper = roomSmallMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public BookingDTO toDTO(Booking entity) {
        return entity == null
                ? null
                : BookingDTO.builder()
                .id(entity.getId())
                .numberOccupants(entity.getNumberOccupants())
                .arrival(entity.getArrival())
                .departure(entity.getDeparture())
                .user(this.userSmallMapper.toDTO(entity.getUser()))
                .room(this.roomSmallMapper.toDTO(entity.getRoom()))
                .build();
    }

    @Override
    public Booking toEntity(BookingDTO dto) {
        return dto == null
                ? null
                : Booking.builder()
                .id(dto.getId())
                .numberOccupants(dto.getNumberOccupants())
                .arrival(dto.getArrival())
                .departure(dto.getDeparture())
                .user(this.userRepository.findById(dto.getUser().getId()).orElse(null))
                .room(this.roomRepository.findById(dto.getRoom().getId()).orElse(null))
                .build();
    }

    public Long toListId(Booking booking) {
        return booking == null
                ? null
                : booking.getId();
    }
}
