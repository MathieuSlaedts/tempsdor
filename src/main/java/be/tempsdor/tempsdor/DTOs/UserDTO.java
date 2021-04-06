package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {

    @NotNull
    Integer id;
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
    Set<SmallRoleDTO> roles;
}
