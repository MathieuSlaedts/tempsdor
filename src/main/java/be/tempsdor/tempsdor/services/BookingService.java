package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.DTOs.RoomDTO;
import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.entities.Booking;
import be.tempsdor.tempsdor.entities.Room;
import be.tempsdor.tempsdor.entities.User;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingService extends CrudService<BookingDTO, BookingDTO, Integer> {
    List<BookingDTO> getAllByUser(Integer userId) throws ElementNotFoundException, ElementsNotFoundException;
    List<BookingDTO> getAllByRoomManagedByUser(Integer userId) throws ElementNotFoundException, ElementsNotFoundException;
}