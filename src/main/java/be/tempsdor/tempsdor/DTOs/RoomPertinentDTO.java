package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomPertinentDTO implements IdentifiedDTO<Integer> {
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
    @NotNull
    Integer manager;
    Set<Integer> bookings;
}
