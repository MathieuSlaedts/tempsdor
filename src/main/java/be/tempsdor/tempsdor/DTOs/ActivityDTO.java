package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotBlank
    @Size(max = 20)
    String name;
    @Size(max = 255)
    String description;
    Set<RoomSmallDTO> rooms;
}
