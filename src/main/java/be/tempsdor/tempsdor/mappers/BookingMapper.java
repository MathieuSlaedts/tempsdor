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
    public BookingDTO toDTO(Booking booking) {
        return booking == null
                ? null
                : BookingDTO.builder()
                .id(booking.getId())
                .numberOccupants(booking.getNumberOccupants())
                .arrivalDatetime(booking.getArrivalDatetime())
                .departureDatetime(booking.getDepartureDatetime())
                .user(this.userSmallMapper.toDTO(booking.getUser()))
                .room(this.roomSmallMapper.toDTO(booking.getRoom()))
                .build();
    }

    @Override
    public Booking toEntity(BookingDTO bookingDTO) {
        return bookingDTO == null
                ? null
                : Booking.builder()
                .id(bookingDTO.getId())
                .numberOccupants(bookingDTO.getNumberOccupants())
                .arrivalDatetime(bookingDTO.getArrivalDatetime())
                .departureDatetime(bookingDTO.getDepartureDatetime())
                .user(this.userRepository.findById(bookingDTO.getUser().getId()).orElse(null))
                .room(this.roomRepository.findById(bookingDTO.getRoom().getId()).orElse(null))
                .build();
    }

    public Integer toListId(Booking booking) {
        return booking == null
                ? null
                : booking.getId();
    }
}
