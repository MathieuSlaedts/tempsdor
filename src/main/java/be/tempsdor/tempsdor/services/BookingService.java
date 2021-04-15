package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.DTOs.BookingPertinentDTO;
import be.tempsdor.tempsdor.DTOs.RoomDTO;
import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingService extends CrudService<BookingPertinentDTO, BookingPertinentDTO, Long> {
    List<BookingPertinentDTO> getAllByUser(Long userId) throws ElementNotFoundException, ElementsNotFoundException;
    List<BookingPertinentDTO> getAllByRoomManagedByUser(Long userId) throws ElementNotFoundException, ElementsNotFoundException;
}