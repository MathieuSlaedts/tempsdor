package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleDTO implements IdentifiedDTO<Integer> {
    @NotNull
    Integer id;
    @NotNull
    String name;
    Set<UserSmallDTO> users;
}
