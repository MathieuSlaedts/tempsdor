package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class UserSmallDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotBlank
    @Size(min = 4, max = 20)
    String username;
    String uri;
}
