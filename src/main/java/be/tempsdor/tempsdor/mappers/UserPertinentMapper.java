package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.entities.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserPertinentMapper {

    private final RoleSmallMapper roleSmallMapper;
    private final RoomSmallMapper roomSmallMapper;
    private final BookingSmallMapper bookingSmallMapper;

    public UserPertinentMapper(RoleSmallMapper smallRoleMapper, RoomSmallMapper roomSmallMapper, BookingSmallMapper bookingSmallMapper) {
        this.roleSmallMapper = smallRoleMapper;
        this.roomSmallMapper = roomSmallMapper;
        this.bookingSmallMapper = bookingSmallMapper;
    }

    public UserPertinentDTO toDTO(User entity) {
        return entity == null
                ? null
                : UserPertinentDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
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
                .bookings(Optional.ofNullable(entity.getBookings())
                        .orElseGet(Collections::emptySet)
                        .stream()
                        .map(this.bookingSmallMapper::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }
}
