package be.tempsdor.tempsdor.controllers;

import be.tempsdor.tempsdor.DTOs.RoleDTO;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import be.tempsdor.tempsdor.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getAll() throws ElementsNotFoundException {
        return this.roleService.getAll();
    }
}
