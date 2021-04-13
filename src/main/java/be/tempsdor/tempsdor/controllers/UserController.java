package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.UserEmailOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPasswordOnlyDTO;
import be.tempsdor.tempsdor.DTOs.UserPertinentDTO;
import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.exceptions.*;
import be.tempsdor.tempsdor.services.CrudService;
import be.tempsdor.tempsdor.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/users")
public class UserController extends AbstractCrudController<UserDTO, UserPertinentDTO, Integer> {

    protected UserController(UserServiceImpl service) {
        super(service);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}/update/email")
    public UserPertinentDTO updateEmailById(@Valid @RequestBody UserEmailOnlyDTO dto, @PathVariable("id") int id) throws ElementNotFoundException {
       return ((UserServiceImpl)service).updateEmailById(dto, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("{id}/update/password")
    public UserPertinentDTO updatePasswordById(@Valid @RequestBody UserPasswordOnlyDTO dto, @PathVariable("id") int id) throws ElementNotFoundException {
        return ((UserServiceImpl)service).updatePasswordById(dto, id);
    }
}
