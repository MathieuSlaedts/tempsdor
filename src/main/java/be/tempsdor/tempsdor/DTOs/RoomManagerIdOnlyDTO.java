package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class RoomManagerIdOnlyDTO implements IdentifiedDTO<Integer> {
    @NotNull
    Integer id;
    @NotNull
    Integer manager;
}
