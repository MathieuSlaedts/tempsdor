package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.BookingSmallDTO;
import be.tempsdor.tempsdor.entities.Booking;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BookingSmallMapper {
    public BookingSmallDTO toDTO(Booking entity) {
        return entity == null
                ? null
                : BookingSmallDTO.builder()
                .id(entity.getId())
                .dates(entity.getArrival() + " -> " + entity.getDeparture())
                .uri(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/bookings/"+entity.getId())
                .build();

    }
}
