package be.tempsdor.tempsdor.constraints;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberOfOccupantsSmallerThanRoomCapacityValidator implements ConstraintValidator<NumberOfOccupantsSmallerThanRoomCapacity, BookingDTO> {

    private final RoomRepository roomRepository;

    public NumberOfOccupantsSmallerThanRoomCapacityValidator(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @SneakyThrows
    @Override
    public boolean isValid(BookingDTO bookingDTO, ConstraintValidatorContext constraintValidatorContext) {
        int numverOccupants = bookingDTO.getNumberOccupants();
        Room room = this.roomRepository.findById( bookingDTO.getRoom().getId())
                .orElseThrow(ElementNotFoundException::new);

        return numverOccupants <= room.getCapacity();
    }
}
