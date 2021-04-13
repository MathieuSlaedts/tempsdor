package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.repositories.BookingRepository;
import be.tempsdor.tempsdor.repositories.RoleRepository;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper implements Mapper<UserDTO, User> {

    private final RoleSmallMapper roleSmallMapper;
    private final RoleRepository roleRepository;

    private final RoomSmallMapper roomSmallMapper;
    private final RoomRepository roomRepository;

    private final BookingSmallMapper bookingSmallMapper;
    private final BookingRepository bookingRepository;

    public UserMapper(RoleSmallMapper roleSmallMapper, RoleRepository roleRepository, RoomSmallMapper roomSmallMapper, RoomRepository roomRepository, BookingSmallMapper bookingSmallMapper, BookingRepository bookingRepository) {
        this.roleSmallMapper = roleSmallMapper;
        this.roleRepository = roleRepository;
        this.roomSmallMapper = roomSmallMapper;
        this.roomRepository = roomRepository;
        this.bookingSmallMapper = bookingSmallMapper;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public UserDTO toDTO(User entity) {
        return entity == null
                ? null
                : UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .lastname(entity.getLastname())
                .firstname(entity.getFirstname())
                .email(entity.getEmail())
                .roles(Optional.ofNullable(entity.getRoles())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.roleSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .rooms(Optional.ofNullable(entity.getRooms())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.roomSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .bookings(entity.getBookings()
                        .stream()
                        .map(this.bookingSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        return dto == null
                ? null
                : User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .lastname(dto.getLastname())
                .firstname(dto.getFirstname())
                .email(dto.getEmail())
                .roles(Optional.ofNullable(dto.getRoles())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(role->this.roleRepository.findById(role.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .rooms(Optional.ofNullable(dto.getRooms())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(room->this.roomRepository.findById(room.getId()).orElse(null))
                        .collect(Collectors.toSet())
                )
                .bookings(Optional.ofNullable(dto.getBookings())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(b->this.bookingRepository.findById(b.getId()).orElse(null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
