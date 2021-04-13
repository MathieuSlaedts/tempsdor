package be.tempsdor.tempsdor.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NumberOfOccupantsSmallerThanRoomCapacityValidator.class })
public @interface NumberOfOccupantsSmallerThanRoomCapacity {
    String message() default "Le nombre d'occupant doit être égal ou inférieur au nombre de place disponible dans la chambre.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
