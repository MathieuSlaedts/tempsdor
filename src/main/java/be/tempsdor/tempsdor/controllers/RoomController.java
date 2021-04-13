package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.*;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.exceptions.MismatchingIdentifersException;
import be.tempsdor.tempsdor.services.CrudService;
import be.tempsdor.tempsdor.services.RoomServiceImpl;
import be.tempsdor.tempsdor.services.UserServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/rooms")
public class RoomController extends AbstractCrudController<RoomDTO, RoomPertinentDTO, Integer> {
    public RoomController(RoomServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/fulldatas")
    public List<RoomDTO> getAllWithFullDatas() throws ElementsNotFoundException {
        return ((RoomServiceImpl)service).getAllWithFullDatas();
    }

    @GetMapping("/by-activity/{id}")
    public List<RoomPertinentDTO> getAllByActivity(@PathVariable Long id) throws ElementsNotFoundException, ElementNotFoundException {
        return ((RoomServiceImpl)service).getAllByActivity(id);
    }

    @GetMapping("/{id}/availability")
    public Map<String, String> getAvailabilityByDateRange(
            @PathVariable Integer id,
            @RequestParam("arrival") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrival,
            @RequestParam("departure") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departure) throws ElementsNotFoundException, ElementNotFoundException {
        Boolean availability = ((RoomServiceImpl)service).getAvailabilityByDateRange(id, arrival, departure);
        HashMap<String, String> map = new HashMap<>();
        map.put("room", id.toString());
        map.put("arrival", arrival.toString());
        map.put("departure", departure.toString());
        map.put("availability", availability ? "Room available" : "Room not available");
        return map;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("{id}/update/manager")
    public RoomPertinentDTO updatePasswordById(@Valid @RequestBody RoomManagerIdOnlyDTO dto, @PathVariable("id") int id) throws ElementNotFoundException, MismatchingIdentifersException {
        return ((RoomServiceImpl)service).updateManagerById(dto, id);
    }
}
