package be.tempsdor.tempsdor.constraints;

import be.tempsdor.tempsdor.DTOs.BookingDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartureDatetimeLaterThanArrivalDatetimeValidator implements ConstraintValidator<DepartureDatetimeLaterThanArrivalDatetime, BookingDTO> {
    @Override
    public boolean isValid(BookingDTO bookingDTO, ConstraintValidatorContext constraintValidatorContext) {
        return bookingDTO.getArrivalDatetime().toLocalDate().isBefore(bookingDTO.getDepartureDatetime().toLocalDate());
    }
}
