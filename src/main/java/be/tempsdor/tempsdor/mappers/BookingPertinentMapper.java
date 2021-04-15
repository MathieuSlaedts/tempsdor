package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.BookingPertinentDTO;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import be.tempsdor.tempsdor.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingPertinentMapper implements Mapper<BookingPertinentDTO, Booking> {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public BookingPertinentMapper(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BookingPertinentDTO toDTO(Booking entity) {
        return entity == null
                ? null
                : BookingPertinentDTO.builder()
                .id(entity.getId())
                .numberOccupants(entity.getNumberOccupants())
                .arrival(entity.getArrival())
                .departure(entity.getDeparture())
                .user(entity.getUser().getId())
                .room(entity.getRoom().getId())
                .build();
    }

    @Override
    public Booking toEntity(BookingPertinentDTO dto) {
        return dto == null
                ? null
                : Booking.builder()
                .id(dto.getId())
                .numberOccupants(dto.getNumberOccupants())
                .arrival(dto.getArrival())
                .departure(dto.getDeparture())
                .user(this.userRepository.findById(dto.getUser()).orElse(null))
                .room(this.roomRepository.findById(dto.getRoom()).orElse(null))
                .build();
    }
}
