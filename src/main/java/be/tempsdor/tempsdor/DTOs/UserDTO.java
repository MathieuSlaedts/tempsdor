package be.tempsdor.tempsdor.DTOs;

import be.tempsdor.tempsdor.entities.Room;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotBlank
    @Size(min = 4, max = 20)
    String username;
    @NotBlank
    @Size(min = 6)
    String password;
    @NotBlank
    @Size(max = 50)
    String lastname;
    @NotBlank
    @Size(max = 50)
    String firstname;
    @NotBlank
    @Email
    String email;
    Set<RoleSmallDTO> roles;
    Set<RoomSmallDTO> rooms;
    Set<BookingSmallDTO> bookings;
}
