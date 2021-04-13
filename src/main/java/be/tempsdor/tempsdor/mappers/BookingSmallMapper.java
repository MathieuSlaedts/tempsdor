package be.tempsdor.tempsdor.mappers;

import be.tempsdor.tempsdor.DTOs.BookingSmallDTO;
import be.tempsdor.tempsdor.entities.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BookingSmallMapper {
    public BookingSmallDTO toDTO(Booking booking) {
        return booking == null
                ? null
                : BookingSmallDTO.builder()
                .id(booking.getId())
                .dates(booking.getArrivalDatetime().toLocalDate() +
                        " -> " +
                        booking.getDepartureDatetime().toLocalDate())
                .build();

    }
}
