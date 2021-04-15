package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.ActivityDTO;
import be.tempsdor.tempsdor.DTOs.BookingDTO;
import be.tempsdor.tempsdor.DTOs.BookingPertinentDTO;
import be.tempsdor.tempsdor.exceptions.*;
import be.tempsdor.tempsdor.services.*;
import org.hibernate.annotations.Entity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/bookings")
public class BookingController extends AbstractCrudController<BookingPertinentDTO, BookingPertinentDTO, Long> {
    public BookingController(BookingServiceImpl service) { super(service); }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public BookingPertinentDTO add(@Valid @RequestBody BookingPertinentDTO dto) throws ElementAlreadyExistsException, ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException {
        return ((BookingServiceImpl)service).add(dto);
    }

    @GetMapping("/by-user/{id}")
    public List<BookingPertinentDTO> getAllByUser(@PathVariable("id") Long id) throws ElementNotFoundException, ElementsNotFoundException {
        return ((BookingServiceImpl)service).getAllByUser(id);
    }

    @GetMapping("/by-room-managed-by-user/{id}")
    public List<BookingPertinentDTO> getAllByRoomManagedByUser(@PathVariable("id") Long id) throws ElementNotFoundException, ElementsNotFoundException {
        return ((BookingServiceImpl)service).getAllByRoomManagedByUser(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/full-datas")
    public List<BookingDTO> getAllWithFullDatas() throws ElementsNotFoundException {
        return ((BookingServiceImpl)service).getAllWithFullDatas();
    }
}
