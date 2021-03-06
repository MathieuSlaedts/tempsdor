package be.tempsdor.tempsdor.DTOs;

import be.tempsdor.tempsdor.constraints.DepartureDatetimeLaterThanArrivalDatetime;
import be.tempsdor.tempsdor.constraints.NumberOfOccupantsSmallerThanRoomCapacity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotNull
    Integer numberOccupants;
    @NotNull
    @Future
    LocalDate arrival;
    @NotNull
    @Future
    LocalDate departure;
    @NotNull
    UserSmallDTO user;
    @NotNull
    RoomSmallDTO room;
}
