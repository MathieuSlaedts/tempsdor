package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.DTOs.UserEmailOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPasswordOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CrudController<FORM, DTO, REPORT, ID> {
    DTO add(FORM form) throws ElementAlreadyExistsException, ElementNotFoundException;
    List<DTO> getAll() throws ElementsNotFoundException;
    DTO getOneById(ID id) throws ElementNotFoundException;
    DTO update(FORM form, ID id) throws ElementNotFoundException;
    REPORT deleteById(ID id) throws ElementNotFoundException;
}
