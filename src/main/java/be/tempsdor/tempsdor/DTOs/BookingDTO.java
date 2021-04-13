package be.tempsdor.tempsdor.DTOs;

import be.tempsdor.tempsdor.constraints.DepartureDatetimeLaterThanArrivalDatetime;
import be.tempsdor.tempsdor.constraints.NumberOfOccupantsSmallerThanRoomCapacity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NumberOfOccupantsSmallerThanRoomCapacity
@DepartureDatetimeLaterThanArrivalDatetime
public class BookingDTO implements IdentifiedDTO<Integer> {
    Integer id;
    Integer numberOccupants;
    @Future
    LocalDateTime arrivalDatetime;
    @Future
    LocalDateTime departureDatetime;
    UserSmallDTO user;
    RoomSmallDTO room;
}
