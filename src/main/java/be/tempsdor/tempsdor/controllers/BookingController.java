package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.services.BookingService;
import be.tempsdor.tempsdor.services.BookingServiceImpl;
import be.tempsdor.tempsdor.services.CrudService;
import be.tempsdor.tempsdor.services.UserServiceImpl;
import org.hibernate.annotations.Entity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/bookings")
public class BookingController extends AbstractCrudController<BookingDTO, BookingDTO, Integer> {
    public BookingController(BookingServiceImpl service) { super(service); }

    @GetMapping("/by-user/{id}")
    public List<BookingDTO> getAllByUser(@PathVariable("id") Integer id) throws ElementNotFoundException, ElementsNotFoundException {
        return ((BookingServiceImpl)service).getAllByUser(id);
    }

    @GetMapping("/by-room-managed-by-user/{id}")
    public List<BookingDTO> getAllByRoomManagedByUser(@PathVariable("id") Integer id) throws ElementNotFoundException, ElementsNotFoundException {
        return ((BookingServiceImpl)service).getAllByRoomManagedByUser(id);
    }
}
