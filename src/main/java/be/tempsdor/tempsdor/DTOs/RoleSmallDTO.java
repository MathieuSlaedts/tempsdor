package be.tempsdor.tempsdor.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleSmallDTO {
    @NotNull
    Integer id;
    @NotNull
    @UniqueElements
    String name;
}
