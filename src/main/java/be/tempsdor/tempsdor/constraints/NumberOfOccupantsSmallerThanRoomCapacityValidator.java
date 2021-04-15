package be.tempsdor.tempsdor.constraints;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.DTOs.BookingPertinentDTO;
import be.tempsdor.tempsdor.DTOs.RoomSmallDTO;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.NullPropertyException;
import be.tempsdor.tempsdor.repositories.RoomRepository;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberOfOccupantsSmallerThanRoomCapacityValidator implements ConstraintValidator<NumberOfOccupantsSmallerThanRoomCapacity, BookingPertinentDTO> {

    private final RoomRepository roomRepository;

    public NumberOfOccupantsSmallerThanRoomCapacityValidator(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @SneakyThrows
    @Override
    public boolean isValid(BookingPertinentDTO dto, ConstraintValidatorContext constraintValidatorContext) {
        if(dto == null)
            throw new IllegalArgumentException();

        if (dto.getNumberOccupants() == null)
            throw new NullPropertyException("NumberOccupants");

        if (dto.getRoom() == null)
            throw new NullPropertyException("room");

        Room room = this.roomRepository.findById(dto.getRoom())
                .orElseThrow(ElementNotFoundException::new);

        return dto.getNumberOccupants() <= room.getCapacity();
    }
}
