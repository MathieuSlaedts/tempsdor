package be.tempsdor.tempsdor.services;

import be.tempsdor.tempsdor.exceptions.ElementNotFoundException;
import be.tempsdor.tempsdor.exceptions.ElementsNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService<RoleDTO> {
    List<RoleDTO> getAll() throws ElementsNotFoundException;
    RoleDTO getByName(String name) throws ElementNotFoundException;
}
