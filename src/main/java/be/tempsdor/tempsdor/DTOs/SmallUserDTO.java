package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class SmallUserDTO {
    @NotNull
    Integer id;
    @NotBlank
    @Size(min = 4, max = 20)
    String username;
}
