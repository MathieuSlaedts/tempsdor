package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPertinentDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotBlank
    @Size(min = 4, max = 20)
    String username;
    @NotBlank
    @Size(max = 50)
    String lastname;
    @NotBlank
    @Size(max = 50)
    String firstname;
    @NotBlank
    @Email
    String email;
    Set<Long> rooms;
    Set<Long> bookings;
}
