package be.tempsdor.tempsdor.DTOs;

import be.tempsdor.tempsdor.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleDTO {
    @NotNull
    Integer id;
    @NotNull
    @UniqueElements
    String name;
}
