package be.tempsdor.tempsdor.DTOs;

import be.tempsdor.tempsdor.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoomDTO implements IdentifiedDTO<Integer> {
    @NotNull
    Integer id;
    @NotNull
    @Min(1)
    Integer capacity;
    @NotBlank
    @Size(min = 10, max = 100)
    String address;
    @NotBlank
    @Size(min = 2, max = 50)
    String city;
    UserSmallDTO user;
    Set<ActivitySmallDTO> activites;
    Set<BookingSmallDTO> bookings;
}
