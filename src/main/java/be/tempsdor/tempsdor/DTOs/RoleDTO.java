package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO implements IdentifiedDTO<Long> {
    @NotNull
    Long id;
    @NotNull
    String name;
    Set<UserSmallDTO> users;
}
