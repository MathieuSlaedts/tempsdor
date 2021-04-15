package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleSmallDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotNull
    String name;
    String uri;
}
