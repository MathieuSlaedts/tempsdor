package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.DTOs.UserEmailOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPasswordOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CrudController<DTO_IN, DTO_OUT, ID> {
    DTO_OUT add(DTO_IN dto) throws ElementAlreadyExistsException, ElementNotFoundException, OwnRoomBookingException, RoomUnavailableException;
    List<DTO_OUT> getAll() throws ElementsNotFoundException;
    DTO_OUT getOneById(ID id) throws ElementNotFoundException;
    DTO_OUT update(DTO_IN dto, ID id) throws ElementNotFoundException, RoomUnavailableException, MismatchingIdentifersException, OwnRoomBookingException;
    Object deleteById(ID id) throws ElementNotFoundException;
}
