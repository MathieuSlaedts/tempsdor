package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.UserDTO;
import be.tempsdor.tempsdor.exceptions.ElementAlreadyExistsException;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.mappers.UserMapper;
import be.tempsdor.tempsdor.services.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/insert")
    public void insert(@Valid @RequestBody UserDTO dto) throws ElementAlreadyExistsException, ElementNotFoundException {
        this.userService.insert(dto);
    }

    @GetMapping
    public List<UserDTO> getAll() throws ElementsNotFoundException {
        return this.userService.getAll();
    }
}
