package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.DTOs.RoleDTO;
import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;

import java.util.List;

public interface RoleService extends CrudService<RoleDTO, RoleDTO, Long> {
    RoleDTO getByName(String name) throws ElementNotFoundException;
}
