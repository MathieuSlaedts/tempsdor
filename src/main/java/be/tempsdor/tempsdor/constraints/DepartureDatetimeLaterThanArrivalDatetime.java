package be.tempsdor.tempsdor.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DepartureDatetimeLaterThanArrivalDatetimeValidator.class })
public @interface DepartureDatetimeLaterThanArrivalDatetime {
    String message() default "La date départ doit être après que la date d'arrivée.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
