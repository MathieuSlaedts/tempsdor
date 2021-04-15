package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingSmallDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    String dates;
    String uri;
}
