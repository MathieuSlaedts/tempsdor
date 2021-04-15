package be.tempsdor.tempsdor.constraints;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.DTOs.BookingPertinentDTO;
import be.tempsdor.tempsdor.exceptions.NullPropertyException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartureDatetimeLaterThanArrivalDatetimeValidator implements ConstraintValidator<DepartureDatetimeLaterThanArrivalDatetime, BookingPertinentDTO> {
    @SneakyThrows
    @Override
    public boolean isValid(BookingPertinentDTO dto, ConstraintValidatorContext constraintValidatorContext) {
        if(dto == null)
            throw new IllegalArgumentException();

        if (dto.getArrival() == null)
            throw new NullPropertyException("arrival");

        if (dto.getDeparture() == null)
            throw new NullPropertyException("departure");

        return dto.getArrival().isBefore(dto.getDeparture());
    }
}
