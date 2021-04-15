package be.tempsdor.tempsdor.DTOs;

import be.tempsdor.tempsdor.constraints.DepartureDatetimeLaterThanArrivalDatetime;
import be.tempsdor.tempsdor.constraints.NumberOfOccupantsSmallerThanRoomCapacity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NumberOfOccupantsSmallerThanRoomCapacity
@DepartureDatetimeLaterThanArrivalDatetime
public class BookingPertinentDTO implements IdentifiedDTO<Long> {
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
    Long user;
    @NotNull
    Long room;
}
